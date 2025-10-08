[Read this page in English](README.md)

# 📨 Requests-Common



**Requests-Common** é uma biblioteca Java projetada para padronizar o ciclo de requisição e resposta em aplicações modernas, especialmente aquelas construídas em uma arquitetura de microsserviços. Ela fornece um conjunto de interfaces e classes abstratas para criar uma estrutura comum e previsível para os dados que fluem através de seus serviços.

O objetivo principal é reduzir o código repetitivo e impor um padrão consistente para lidar com requisições, respostas, credenciais de usuário, paginação e erros.

---

## ✨ Recursos Principais

*   **Envelopes de Requisição/Resposta:** Wrappers genéricos (`IRequest`, `IResponse`) para seus DTOs, enriquecidos com metadados como `requestId` e `traceId`.
*   **Manipulação de Credenciais de Usuário:** Uma forma padronizada de passar informações do usuário (`IUserCredential`) a cada requisição.
*   **Paginação Simplificada:** Classes abstratas (`AbsPageableRequest`, `PageableDTO`) para implementar facilmente consultas paginadas.
*   **Respostas de Erro Padronizadas:** Um DTO (`ApiErrorDTO`) para enviar mensagens de erro consistentes e detalhadas.
*   **Validação de Campos:** Uma anotação `@RequiredField` para marcar campos como obrigatórios.

---

## 🛠️ Instalação

Para adicionar o **Requests-Common** ao seu projeto Maven, adicione a seguinte dependência ao seu `pom.xml`. Certifique-se de usar a versão mais recente disponível.

```xml
<dependency>
    <groupId>dev.nishisan</groupId>
    <artifactId>requests-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🌷 Auto-configuração com Spring Boot

Esta biblioteca inclui um recurso de auto-configuração para Spring Boot que simplifica sua integração em aplicações Spring. Quando ativado, ele registra automaticamente um `ResponseStatusAdvice`, que permite que o código de status HTTP de uma resposta seja definido dinamicamente a partir de objetos `AbsResponse` ou `BasicException`.

### Ativando a Auto-configuração

Para ativar esse recurso, adicione a seguinte propriedade ao seu `application.properties` ou `application.yml`:

**application.properties:**
```properties
nishi.requests.common.enabled=true
```

**application.yml:**
```yml
nishi:
  requests:
    common:
      enabled: true
```

Com essa configuração, qualquer método de controller que retorne um `IResponse` terá seu código de status HTTP ajustado automaticamente com base no valor definido em `response.setStatusCode()`.

### Como Funciona

O mecanismo de auto-configuração baseia-se em recursos padrão do Spring Boot:

1.  **`AutoConfiguration.imports`**: O arquivo `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` registra a classe `NishiRequestsCommonAutoConfiguration` no Spring Boot, permitindo que ela seja processada como uma classe de auto-configuração.
2.  **Configuração Condicional**: A classe `NishiRequestsCommonAutoConfiguration` é anotada com `@ConditionalOnProperty`, que ativa a configuração somente quando a propriedade `nishi.requests.common.enabled` é definida como `true`.
3.  **Response Body Advice**: Quando ativada, a configuração registra um bean `ResponseStatusAdvice`. Esse bean é um `@RestControllerAdvice` que implementa `ResponseBodyAdvice`. Ele intercepta a resposta antes de ser enviada ao cliente, verifica se o corpo é uma instância de `AbsResponse` ou `BasicException` e utiliza o valor de `getStatusCode()` do objeto para definir o status HTTP final da resposta.

---

## 🚀 Exemplos de Uso

### 1. Requisição e Resposta Básica

Primeiro, defina a estrutura de dados para o seu domínio.

```java
// ProdutoDTO.java
public class ProdutoDTO {
    private String sku;
    private String nome;
    private double preco;

    // Getters e Setters
}
```

Em seguida, crie classes de Requisição e Resposta específicas estendendo as classes abstratas.

```java
// CriarProdutoRequest.java
public class CriarProdutoRequest extends AbsRequest<ProdutoDTO> {
    public CriarProdutoRequest(ProdutoDTO payload) {
        super(payload);
    }
}

// ProdutoResponse.java
public class ProdutoResponse extends AbsResponse<ProdutoDTO> {
    public ProdutoResponse(ProdutoDTO payload) {
        super(payload);
    }
}
```

Agora você pode usar essas classes em seus serviços ou controladores.

```java
// ProdutoService.java
public ProdutoResponse criarProduto(CriarProdutoRequest request) {
    ProdutoDTO dadosProduto = request.getPayload();
    
    // ... lógica para salvar o produto ...

    // Cria e retorna uma resposta padronizada
    ProdutoResponse response = new ProdutoResponse(dadosProduto);
    response.setStatusCode(201);
    response.setSourceRequestId(request.getRequestId());
    return response;
}
```

### 2. Requisição Paginada

Para consultar uma lista de itens com paginação, use `AbsPageableRequest`.

```java
// ListarProdutosRequest.java
public class ListarProdutosRequest extends AbsPageableRequest {
    public ListarProdutosRequest(int page, int size) {
        super(page, size);
    }
}
```

Seu serviço pode então usar os dados de paginação do payload.

```java
// ProdutoService.java
public IResponse<Page<ProdutoDTO>> listarProdutos(ListarProdutosRequest request) {
    PageableDTO infoPagina = request.getPayload();
    
    // Usa o Pageable do Spring Data
    Pageable pageable = PageRequest.of(
        infoPagina.getPage(),
        infoPagina.getSize(),
        Sort.by(infoPagina.getDirection(), infoPagina.getSort())
    );

    Page<ProdutoDTO> paginaProdutos = produtoRepository.findAll(pageable);

    // O construtor de AbsResponse lida automaticamente com objetos Page
    return new AbsResponse<Page<ProdutoDTO>>(paginaProdutos) {};
}
```

### 3. Manipulando Credenciais de Usuário

Anexe as credenciais do usuário a uma requisição para identificar o chamador.

```java
// Cria uma requisição e define o usuário
var request = new CriarProdutoRequest(new ProdutoDTO());
request.setRequestId(UUID.randomUUID().toString());

// GenericUserCredential pode conter um ID de usuário ou dados mais complexos
IUserCredential user = new GenericUserCredential("user-123");
request.setUserCredential(user);

// Passa a requisição para o serviço
produtoService.criarProduto(request);
```

---

## 📦 Construindo a Partir do Código-Fonte

Para construir o projeto localmente, clone o repositório e execute o seguinte comando Maven:

```bash
mvn clean package
```

---

## 📄 Licença

Este projeto está licenciado sob a [Licença Pública Geral GNU v3.0](https://www.gnu.org/licenses/gpl-3.0.html). Veja o arquivo `LICENSE` para mais detalhes.

---

## 📬 Contato

Para dúvidas ou suporte, por favor, abra uma issue ou entre em contato em `github@nishisan.dev`.
