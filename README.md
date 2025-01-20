# Restaurant Management System

This project is a Spring Boot application for managing restaurants, including functionalities for searching, adding to favorites, and managing cart items. The application uses a RESTful API to handle various operations related to restaurants, food items, and user interactions.

## Key Features

1. **Restaurant Management**:
   - Create, update, and delete restaurants.
   - Search restaurants by various attributes such as name, cuisine type, description, food items, and addresses.
   - Retrieve all restaurants or a specific restaurant by ID.

2. **User Favorites**:
   - Add or remove restaurants from user favorites.
   - Retrieve favorite restaurants of a user.

3. **Cart Management**:
   - Add, update, and remove items in the user's cart.
   - Clear the cart and calculate the total cost of items in the cart.
   - Manage cart items by food ID.

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building the application.
- **Maven**: Build and dependency management tool.
- **JPA/Hibernate**: ORM for database interactions.
- **Postman**: Tool for testing the API endpoints.

## Project Structure

- **Controller**: Handles HTTP requests and responses.
- **Service**: Contains business logic.
- **Repository**: Interacts with the database.
- **Entity**: Represents database tables.
- **DTO**: Data Transfer Objects for transferring data between layers.
- **Request**: Classes for handling request payloads.

## Endpoints

### Restaurant Endpoints

- **GET /open/restaurants**: Retrieve all restaurants.
- **GET /open/restaurants/{id}**: Retrieve a specific restaurant by ID.
- **GET /open/restaurants/search**: Search restaurants by keyword.
- **GET /open/restaurants/search/anything**: Search restaurants by any attribute.
- **PUT /open/restaurants/{id}/add-favourites**: Add a restaurant to favorites.
- **DELETE /open/restaurants/{id}/remove-favourites**: Remove a restaurant from favorites.
- **GET /open/restaurants/foods**: Get all food items of a restaurant.
- **GET /open/restaurants/food**: Get restaurant by food ID.
- **GET /open/restaurants/favorites**: Get favorite restaurants of a user.
- **GET /open/restaurants/favorites/{id}**: Get favorite restaurant by restaurant ID.

### Cart Endpoints

- **PUT /api/cart/add**: Add an item to the cart.
- **PUT /api/cart-item/update**: Update the quantity of a cart item.
- **DELETE /api/cart-item/{id}/remove**: Remove an item from the cart.
- **PUT /api/cart/clear**: Clear the cart.
- **GET /api/cart**: Retrieve the user's cart.
- **GET /api/cart/calculate**: Calculate the total cost of items in the cart.
- **DELETE /api/cart-item/food/{foodId}/remove**: Remove a cart item by food ID.
- **PUT /api/cart-item/food/{foodId}/update**: Update the quantity of a cart item by food ID.
- **GET /api/cart-item/food/{foodId}**: Get a cart item by food ID.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- Postman (for API testing)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/ayanmohammadshobuj/easyFood.git
   cd your-repository-name

1. Build the project:  
mvn clean install
2. Run the application:  
mvn spring-boot:run
Testing the API
Use Postman to test the API endpoints. Refer to the endpoints section for the available API operations.  
License
This project is licensed under the MIT License. See the LICENSE file for details.
