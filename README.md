<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Management System</title>
</head>
<body>
    <h1>Restaurant Management System</h1>
    <p>This project is a Spring Boot application for managing restaurants, including functionalities for searching, adding to favorites, and managing cart items. The application uses a RESTful API to handle various operations related to restaurants, food items, and user interactions.</p>

    <h2>Key Features</h2>
    <ul>
        <li><strong>Restaurant Management:</strong>
            <ul>
                <li>Create, update, and delete restaurants.</li>
                <li>Search restaurants by various attributes such as name, cuisine type, description, food items, and addresses.</li>
                <li>Retrieve all restaurants or a specific restaurant by ID.</li>
            </ul>
        </li>
        <li><strong>User Favorites:</strong>
            <ul>
                <li>Add or remove restaurants from user favorites.</li>
                <li>Retrieve favorite restaurants of a user.</li>
            </ul>
        </li>
        <li><strong>Cart Management:</strong>
            <ul>
                <li>Add, update, and remove items in the user's cart.</li>
                <li>Clear the cart and calculate the total cost of items in the cart.</li>
                <li>Manage cart items by food ID.</li>
            </ul>
        </li>
    </ul>

    <h2>Technologies Used</h2>
    <ul>
        <li><strong>Java:</strong> Programming language.</li>
        <li><strong>Spring Boot:</strong> Framework for building the application.</li>
        <li><strong>Maven:</strong> Build and dependency management tool.</li>
        <li><strong>JPA/Hibernate:</strong> ORM for database interactions.</li>
        <li><strong>Postman:</strong> Tool for testing the API endpoints.</li>
    </ul>

    <h2>Project Structure</h2>
    <ul>
        <li><strong>Controller:</strong> Handles HTTP requests and responses.</li>
        <li><strong>Service:</strong> Contains business logic.</li>
        <li><strong>Repository:</strong> Interacts with the database.</li>
        <li><strong>Entity:</strong> Represents database tables.</li>
        <li><strong>DTO:</strong> Data Transfer Objects for transferring data between layers.</li>
        <li><strong>Request:</strong> Classes for handling request payloads.</li>
    </ul>

    <h2>Endpoints</h2>
    <h3>Restaurant Endpoints</h3>
    <ul>
        <li><strong>GET /open/restaurants:</strong> Retrieve all restaurants.</li>
        <li><strong>GET /open/restaurants/{id}:</strong> Retrieve a specific restaurant by ID.</li>
        <li><strong>GET /open/restaurants/search:</strong> Search restaurants by keyword.</li>
        <li><strong>GET /open/restaurants/search/anything:</strong> Search restaurants by any attribute.</li>
        <li><strong>PUT /open/restaurants/{id}/add-favourites:</strong> Add a restaurant to favorites.</li>
        <li><strong>DELETE /open/restaurants/{id}/remove-favourites:</strong> Remove a restaurant from favorites.</li>
        <li><strong>GET /open/restaurants/foods:</strong> Get all food items of a restaurant.</li>
        <li><strong>GET /open/restaurants/food:</strong> Get restaurant by food ID.</li>
        <li><strong>GET /open/restaurants/favorites:</strong> Get favorite restaurants of a user.</li>
        <li><strong>GET /open/restaurants/favorites/{id}:</strong> Get favorite restaurant by restaurant ID.</li>
    </ul>

    <h3>Cart Endpoints</h3>
    <ul>
        <li><strong>PUT /api/cart/add:</strong> Add an item to the cart.</li>
        <li><strong>PUT /api/cart-item/update:</strong> Update the quantity of a cart item.</li>
        <li><strong>DELETE /api/cart-item/{id}/remove:</strong> Remove an item from the cart.</li>
        <li><strong>PUT /api/cart/clear:</strong> Clear the cart.</li>
        <li><strong>GET /api/cart:</strong> Retrieve the user's cart.</li>
        <li><strong>GET /api/cart/calculate:</strong> Calculate the total cost of items in the cart.</li>
        <li><strong>DELETE /api/cart-item/food/{foodId}/remove:</strong> Remove a cart item by food ID.</li>
        <li><strong>PUT /api/cart-item/food/{foodId}/update:</strong> Update the quantity of a cart item by food ID.</li>
        <li><strong>GET /api/cart-item/food/{foodId}:</strong> Get a cart item by food ID.</li>
    </ul>

    <h2>Getting Started</h2>
    <h3>Prerequisites</h3>
    <ul>
        <li>Java 17 or higher</li>
        <li>Maven 3.6.0 or higher</li>
        <li>Postman (for API testing)</li>
    </ul>

    <h3>Installation</h3>
    <ol>
        <li>Clone the repository:
            <pre><code>git clone https://github.com/your-username/your-repository-name.git
cd your-repository-name</code></pre>
        </li>
        <li>Build the project:
            <pre><code>mvn clean install</code></pre>
        </li>
        <li>Run the application:
            <pre><code>mvn spring-boot:run</code></pre>
        </li>
    </ol>

    <h3>Testing the API</h3>
    <p>Use Postman to test the API endpoints. Refer to the endpoints section for the available API operations.</p>

    <h2>License</h2>
    <p>This project is licensed under the MIT License. See the <code>LICENSE</code> file for details.</p>
</body>
</html>
