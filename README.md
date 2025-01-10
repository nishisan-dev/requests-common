# 📨 Requests-Common

**Requests-Common** é uma biblioteca em Java projetada para facilitar a construção e manutenção de requisições HTTP padronizadas. Ela oferece uma interface comum para realizar chamadas HTTP, com foco em reutilização de código e simplicidade na configuração.

---

## 🛠️ Instalação 🛠️

Para adicionar **Requests-Common** ao seu projeto Maven, inclua a seguinte dependencia no seu arquivo `pom.xml`:

```xml
<dependency>
    <groupId>dev.nishisan</groupId>
    <artifactId>requests-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🔍 Exemplo de uso 🔍

Abaixo, um exemplo de como usar a biblioteca para padronizar requisições e respostas:

```java
// Classe de requisição personalizada com tipo MyComplexType
public class MyComplexTypeRequest extends AbsRequest<MyComplexType> {
    
}
```
```java
// Classe de resposta personalizada com o tipo MyComplexType
public class MyComplexTypeResponse extends AbsResponse<MyComplexTypeResponse> {
    public MyComplexTypeResponse(MyComplexTypeResponse payload) {
        super(payload);
    }
}
```
```java

// Classe representando o tipo de dado MyComplexType
public class MyComplexType {
    private String name;
    private Integer id;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}


```

---



Este projeto é licenciado sob a [Licença GNU v2](LICENSE). Consulte o arquivo de licença para mais detalhes.

---

## 📬 Contato 📬

Para dúvidas ou suporte, entre em contato pelo e-mail: `github@nishisan.dev`.

