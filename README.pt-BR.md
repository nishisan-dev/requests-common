[Read this page in English](README.md)

# 📨 Requests-Common

**Requests-Common** é uma biblioteca Java leve que oferece uma linguagem compartilhada para lidar com requisições de entrada e respostas de saída. Ela fornece envelopes tipados, ajudantes de paginação, manipulação de credenciais, contratos de erro e auto-configuração opcional para Spring Boot, garantindo que cada microsserviço da sua arquitetura troque dados de maneira previsível.

## 📚 Sumário
- [Por que usar Requests-Common?](#-por-que-usar-requests-common)
- [Instalação](#-instalação)
- [Guia Rápido](#-guia-rápido)
- [Blocos de Construção](#-blocos-de-construção)
  - [Requisições](#requisições)
  - [Respostas](#respostas)
  - [Credenciais de Usuário](#credenciais-de-usuário)
  - [Paginação](#paginação)
  - [Validação](#validação)
  - [Tratamento de Erros](#tratamento-de-erros)
- [Auto-configuração Spring Boot](#-auto-configuração-spring-boot)
- [Exemplo REST de ponta a ponta](#-exemplo-rest-de-ponta-a-ponta)
- [Compilando o projeto](#-compilando-o-projeto)
- [Licença](#-licença)
- [Contato](#-contato)

---

## 🤔 Por que usar Requests-Common?

Quando cada serviço em um sistema distribuído cria seus próprios envelopes de requisição e resposta, é comum repetir funcionalidades (IDs, cabeçalhos, metadados de paginação, payloads de erro etc.). O Requests-Common centraliza esses padrões para que você possa:

* compartilhar contratos de DTO entre serviços JVM sem duplicação;
* enriquecer respostas automaticamente com metadados como `responseId`, tamanho do payload e total de páginas;【F:src/main/java/dev/nishisan/requests/common/response/AbsResponse.java†L34-L114】
* anexar credenciais, IDs de rastreio e cabeçalhos personalizados a cada requisição de forma consistente;【F:src/main/java/dev/nishisan/requests/common/request/AbsRequest.java†L30-L97】
* expor objetos de erro e códigos HTTP uniformes em suas APIs.【F:src/main/java/dev/nishisan/requests/common/dto/ApiErrorDTO.java†L23-L78】【F:src/main/java/dev/nishisan/requests/common/exception/BasicException.java†L10-L90】

---

## 📦 Instalação

Adicione a dependência ao seu build. Substitua `1.2.12` pela versão mais recente quando necessário.

<details>
<summary><strong>Maven</strong></summary>

```xml
<dependency>
    <groupId>dev.nishisan</groupId>
    <artifactId>requests-common</artifactId>
    <version>1.2.12</version>
</dependency>
```

</details>

<details>
<summary><strong>Gradle (Kotlin DSL)</strong></summary>

```kotlin
dependencies {
    implementation("dev.nishisan:requests-common:1.2.12")
}
```

</details>

<details>
<summary><strong>Gradle (Groovy DSL)</strong></summary>

```groovy
dependencies {
    implementation 'dev.nishisan:requests-common:1.2.12'
}
```

</details>

---

## ⚡ Guia Rápido

O trecho a seguir demonstra o fluxo típico de modelagem para um endpoint de criação de produto.

```java
// 1. Defina o payload
public record ProdutoDTO(String sku, String nome, double preco) {}

// 2. Encapsule o payload em uma requisição
public class CriarProdutoRequest extends AbsRequest<ProdutoDTO> {
    public CriarProdutoRequest(ProdutoDTO payload) {
        super(payload);
    }
}

// 3. Encapsule o payload em uma resposta
public class ProdutoResponse extends AbsResponse<ProdutoDTO> {
    public ProdutoResponse(ProdutoDTO payload) {
        super(payload);
        setStatusCode(201);
    }
}

// 4. Utilize os envelopes dentro do serviço
@Service
public class ProdutoService {
    public ProdutoResponse criar(CriarProdutoRequest request) {
        var credenciais = request.getUserCredential();
        var traceId = request.getTraceId();

        ProdutoDTO salvo = salvarProduto(request.getPayload());

        ProdutoResponse response = new ProdutoResponse(salvo);
        response.setSourceRequestId(request.getRequestId());
        response.setTraceId(traceId);
        response.addResponseHeader("X-Correlation-Id", traceId);
        return response;
    }
}
```

> 💡 `AbsResponse` gera automaticamente um `responseId` e calcula metadados do payload, como tamanho de coleções ou total de páginas para objetos `Page<?>`.【F:src/main/java/dev/nishisan/requests/common/response/AbsResponse.java†L42-L101】

---

## 🧱 Blocos de Construção

### Requisições

* `AbsRequest<T>` implementa `IRequest<T>` e oferece campos para `requestId`, `traceId`, payload, cabeçalhos arbitrários e credencial de usuário.【F:src/main/java/dev/nishisan/requests/common/request/AbsRequest.java†L30-L97】
* Use `setRequestId`, `setTraceId` e `addRequestHeader` para encaminhar metadados; os cabeçalhos ficam guardados em um `ConcurrentHashMap`, seguro para múltiplas threads.【F:src/main/java/dev/nishisan/requests/common/request/AbsRequest.java†L30-L79】

### Respostas

* `AbsResponse<T>` implementa `IResponse<T>` e é responsável por:
  * gerar um `responseId` único com `UUID.randomUUID()`;
  * manter `sourceRequestId`, `traceId` e o código HTTP `statusCode`;
  * calcular o tamanho do payload para `List`, `Map` e `Page`;
  * armazenar cabeçalhos de resposta.【F:src/main/java/dev/nishisan/requests/common/response/AbsResponse.java†L34-L114】
* O `setStatusCode` funciona em conjunto com o advice do Spring Boot, que propaga o código HTTP automaticamente.【F:src/main/java/dev/nishisan/requests/common/spring/servlet/ResponseStatusAdvice.java†L22-L72】

### Credenciais de Usuário

* Crie suas próprias credenciais estendendo `AbsUserCredential<T>` ou reutilize `GenericUserCredential` quando precisar apenas de um ID e dados opcionais.【F:src/main/java/dev/nishisan/requests/common/uc/AbsUserCredential.java†L22-L70】【F:src/main/java/dev/nishisan/requests/common/uc/GenericUserCredential.java†L17-L32】
* Anexe-as à requisição via `request.setUserCredential(...)` e recupere depois para autorizar operações.【F:src/main/java/dev/nishisan/requests/common/request/AbsRequest.java†L32-L97】

### Paginação

* `AbsPageableRequest` monta um payload `PageableDTO` automaticamente, bastando estender a classe e informar os parâmetros de paginação.【F:src/main/java/dev/nishisan/requests/common/request/AbsPageableRequest.java†L24-L38】
* `PageableDTO` armazena `page`, `size`, `sort`, `direction` e uma `query` opcional, cobrindo a maioria dos cenários de paginação.【F:src/main/java/dev/nishisan/requests/common/dto/PageableDTO.java†L23-L94】

### Validação

* Utilize `@RequiredField` para marcar campos obrigatórios. Valores permitidos podem ser restringidos com as declarações internas `@AllowedValue` (semelhante a um enum).【F:src/main/java/dev/nishisan/requests/common/annotations/RequiredField.java†L1-L15】
* Como a anotação tem retenção em tempo de execução, é possível refletir sobre ela em camadas de serviço ou validação para aplicar regras personalizadas.【F:src/main/java/dev/nishisan/requests/common/annotations/RequiredField.java†L5-L15】

### Tratamento de Erros

* Estenda `BasicException` para representar erros de domínio. Ela guarda a requisição original, um código HTTP customizável e um `Map` de `details` que pode ser enriquecido fluentemente através do método `details(key, value)` sobrescrito na subclasse.【F:src/main/java/dev/nishisan/requests/common/exception/BasicException.java†L10-L90】
* Padronize o corpo das respostas de erro com `ApiErrorDTO`, que carrega mensagem, nome da classe, status, detalhes e, opcionalmente, um snapshot da requisição.【F:src/main/java/dev/nishisan/requests/common/dto/ApiErrorDTO.java†L23-L78】

---

## 🌷 Auto-configuração Spring Boot

O Requests-Common oferece uma auto-configuração que registra `ResponseStatusAdvice` em aplicações Spring Boot baseadas em servlet. Ao habilitá-la, qualquer método de controller que retorne um `AbsResponse` (ou lance um `BasicException`) terá o código HTTP sincronizado automaticamente com o valor definido no objeto.【F:src/main/java/dev/nishisan/requests/common/spring/servlet/ResponseStatusAdvice.java†L22-L72】【F:src/main/java/dev/nishisan/requests/common/spring/servlet/config/NishiRequestsCommonAutoConfiguration.java†L1-L22】

Habilite com a propriedade abaixo:

```properties
# application.properties
nishi.requests.common.enabled=true
```

Ou em YAML:

```yaml
# application.yml
nishi:
  requests:
    common:
      enabled: true
```

Como a configuração é condicional a um aplicativo web servlet, é seguro adicionar a dependência a bibliotecas ou serviços não web sem ativar o advice.【F:src/main/java/dev/nishisan/requests/common/spring/servlet/config/NishiRequestsCommonAutoConfiguration.java†L5-L22】

---

## 🔁 Exemplo REST de ponta a ponta

O controller abaixo mostra como as peças se encaixam em um projeto Spring Boot:

```java
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ProdutoResponse criar(@RequestBody ProdutoDTO dto,
                                 @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        CriarProdutoRequest request = new CriarProdutoRequest(dto);
        request.setRequestId(UUID.randomUUID().toString());
        request.setTraceId(traceId);
        request.setUserCredential(new GenericUserCredential("user-123"));
        request.addRequestHeader("X-Trace-Id", traceId);

        return service.criar(request);
    }

    @GetMapping
    public AbsResponse<Page<ProdutoDTO>> listar(@RequestParam int page, @RequestParam int size) {
        ListarProdutosRequest request = new ListarProdutosRequest(page, size);
        AbsResponse<Page<ProdutoDTO>> response = service.listar(request);
        response.setStatusCode(200);
        return response;
    }

    @GetMapping("/{sku}")
    public ProdutoResponse buscarPorSku(@PathVariable String sku) throws ProdutoNaoEncontradoException {
        return service.buscarPorSku(sku);
    }
}
```

Tratamento de erro com uma subclasse de `BasicException`:

```java
public class ProdutoNaoEncontradoException extends BasicException {
    public ProdutoNaoEncontradoException(String sku, IRequest<?> request) {
        super("Produto não encontrado", request, 404, Map.of("sku", sku));
    }

    @Override
    public ProdutoNaoEncontradoException details(String key, Object value) {
        addDetail(key, value);
        return this;
    }
}

@RestControllerAdvice
public class ProdutoExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorDTO> handle(ProdutoNaoEncontradoException ex) {
        ApiErrorDTO erro = new ApiErrorDTO();
        erro.setMsg(ex.getMessage());
        erro.setStatusCode(ex.getStatusCode());
        erro.setClassName(ex.getClass().getName());
        erro.setDetails(ex.details());
        erro.setRequest(ex.getRequest());
        return ResponseEntity.status(ex.getStatusCode()).body(erro);
    }
}
```

Com a auto-configuração habilitada, o `ResponseStatusAdvice` garante que o status HTTP reflita o valor definido no `AbsResponse` ou no `BasicException`, evitando configurar o código manualmente em cada controller.【F:src/main/java/dev/nishisan/requests/common/spring/servlet/ResponseStatusAdvice.java†L22-L72】

---

## 🛠️ Compilando o projeto

Clone o repositório e execute:

```bash
mvn clean package
```

O projeto utiliza Java 21 e Spring Boot 3.5; verifique se o toolchain está disponível antes de compilar.【F:pom.xml†L40-L48】

---

## 📄 Licença

Este projeto é licenciado sob a [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html). Consulte o arquivo `LICENSE` para mais detalhes.

---

## 📬 Contato

Dúvidas ou sugestões? Abra uma issue ou envie um e-mail para `github@nishisan.dev`.
