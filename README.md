[Leia esta página em Português do Brasil](README.pt-BR.md)

# 📨 Requests-Common



**Requests-Common** is a Java library designed to standardize the request and response cycle in modern applications, especially those built on a microservices architecture. It provides a set of interfaces and abstract classes to create a common, predictable structure for data flowing through your services.

The main goal is to reduce boilerplate code and enforce a consistent pattern for handling requests, responses, user credentials, pagination, and errors.

---

## ✨ Core Features

*   **Request/Response Envelopes:** Generic wrappers (`IRequest`, `IResponse`) for your DTOs, enriched with metadata like `requestId` and `traceId`.
*   **User Credential Handling:** A standardized way to pass user information (`IUserCredential`) with every request.
*   **Simplified Pagination:** Abstract classes (`AbsPageableRequest`, `PageableDTO`) to easily implement paginated queries.
*   **Standardized Error Responses:** A DTO (`ApiErrorDTO`) for sending consistent and detailed error messages.
*   **Field Validation:** A `@RequiredField` annotation to mark fields as mandatory.

---

## 🛠️ Installation

To add **Requests-Common** to your Maven project, add the following dependency to your `pom.xml`. Make sure to use the latest version available.

```xml
<dependency>
    <groupId>dev.nishisan</groupId>
    <artifactId>requests-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

## 🚀 Usage Examples

### 1. Basic Request and Response

First, define the data structure for your domain.

```java
// ProductDTO.java
public class ProductDTO {
    private String sku;
    private String name;
    private double price;

    // Getters and Setters
}
```

Next, create specific Request and Response classes by extending the abstracts.

```java
// CreateProductRequest.java
public class CreateProductRequest extends AbsRequest<ProductDTO> {
    public CreateProductRequest(ProductDTO payload) {
        super(payload);
    }
}

// ProductResponse.java
public class ProductResponse extends AbsResponse<ProductDTO> {
    public ProductResponse(ProductDTO payload) {
        super(payload);
    }
}
```

Now you can use these classes in your services or controllers.

```java
// ProductService.java
public ProductResponse createProduct(CreateProductRequest request) {
    ProductDTO productData = request.getPayload();
    
    // ... logic to save the product ...

    // Create and return a standardized response
    ProductResponse response = new ProductResponse(productData);
    response.setStatusCode(201);
    response.setSourceRequestId(request.getRequestId());
    return response;
}
```

### 2. Paginated Request

To query a list of items with pagination, use `AbsPageableRequest`.

```java
// ListProductsRequest.java
public class ListProductsRequest extends AbsPageableRequest {
    public ListProductsRequest(int page, int size) {
        super(page, size);
    }
}
```

Your service can then use the pagination data from the payload.

```java
// ProductService.java
public IResponse<Page<ProductDTO>> listProducts(ListProductsRequest request) {
    PageableDTO pageInfo = request.getPayload();
    
    // Use Spring Data's Pageable
    Pageable pageable = PageRequest.of(
        pageInfo.getPage(),
        pageInfo.getSize(),
        Sort.by(pageInfo.getDirection(), pageInfo.getSort())
    );

    Page<ProductDTO> productPage = productRepository.findAll(pageable);

    // The AbsResponse constructor automatically handles Page objects
    return new AbsResponse<Page<ProductDTO>>(productPage) {};
}
```

### 3. Handling User Credentials

Attach user credentials to a request to identify the caller.

```java
// Create a request and set the user
var request = new CreateProductRequest(new ProductDTO());
request.setRequestId(UUID.randomUUID().toString());

// GenericUserCredential can hold a user ID or more complex data
IUserCredential user = new GenericUserCredential("user-123");
request.setUserCredential(user);

// Pass the request to the service
productService.createProduct(request);
```

---

## 📦 Building from Source

To build the project locally, clone the repository and run the following Maven command:

```bash
mvn clean package
```

---

## 📄 License

This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.html). See the `LICENSE` file for more details.

---

## 📬 Contact

For questions or support, please open an issue or contact us at `github@nishisan.dev`.
