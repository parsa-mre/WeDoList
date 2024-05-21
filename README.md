# **WeDoList: Collaborative Todo List with Real-time Syncing**

**Tech Stack:** Spring Boot | SockJS | Redis | MongoDB | ReactJS | TypeScript
<img width="918" alt="Screenshot 2024-05-21 at 12 04 09 PM" src="https://github.com/parsa-mre/WeDoList/assets/75425384/a7b1f3a2-4324-484a-918e-704c74c07af7">


## Features
- Created Conflict-free Replicated Data Type (**CRDT**) for todo list items.
- Achieved real-time synchronization across clients using **WebSockets**.
- Implemented **asynchronous writing to database** to ensure incoming todo list updates are quickly propagated to other clients, enhancing real-time collaboration.
- Ensured **offline capability** by utilizing service workers and local storage to cache data and enable uninterrupted access to todo lists even without an internet connection.

## Architecture Explanation

### Diagram Overview

The architecture of WeDoList is designed to facilitate real-time collaborative editing of a todo list, ensuring all clients stay synchronized with the latest updates. Here’s a detailed explanation of each component and their interactions:

1. **MongoDB:**
   - **Role:** Acts as the primary database to store the most recent version of the todo list.
   - **Interaction:** Provides the latest list to the Merge Lists Service and receives updates from it.

2. **Merge Lists Service:**
   - **Role:** Central service responsible for merging updates and maintaining the integrity of the todo list.
   - **Interaction:** 
     - Fetches the latest list from MongoDB.
     - Processes updates and merges changes.
     - Sends the updated list back to MongoDB.
     - Provides the updated list to the API/WebSocket service.

3. **API/WebSocket Service:**
   - **Role:** Manages client connections and ensures real-time synchronization.
   - **Interaction:**
     - Receives the list of clients from Redis.
     - Distributes the updated todo list to all connected clients using WebSockets.
     - Receives updates from clients and forwards them to the Merge Lists Service for processing.

4. **Redis:**
   - **Role:** Stores the list of currently connected clients.
   - **Interaction:** Provides the list of clients to the API/WebSocket service to manage client communications effectively.

5. **Clients:**
   - **Role:** Users interacting with the todo list application.
   - **Interaction:**
     - Receive real-time updates of the todo list from the API/WebSocket service.
     - Send updates when they make changes to their todo list.

### Workflow

1. **Initialization:**
   - The latest todo list is fetched from MongoDB by the Merge Lists Service.
   - This list is then provided to the API/WebSocket service, which handles client synchronization.

2. **Client Interaction:**
   - Clients connect to the application through the API/WebSocket service.
   - Redis maintains a list of these connected clients.

3. **Real-time Updates:**
   - When a client updates their todo list, the update is sent to the API/WebSocket service.
   - The API/WebSocket service forwards this update to the Merge Lists Service.
   - The Merge Lists Service processes the update, merges any changes, and updates MongoDB.
   - The updated list is then sent back to the API/WebSocket service, which broadcasts it to all connected clients, ensuring everyone has the latest version.

4. **Offline Capability:**
   - Clients can continue to use the application offline thanks to service workers and local storage, which cache the data locally. Once reconnected, any changes made offline are synchronized with the server.

This architecture ensures a seamless, real-time collaborative experience for users while maintaining data integrity and consistency across all clients.


