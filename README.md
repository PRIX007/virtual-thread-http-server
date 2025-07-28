# 🚀 Simple Java HTTP Server (Virtual Threads & Socket-Based)

This project is a **lightweight multithreaded HTTP server** built using **Java Virtual Threads** (Project Loom) and **Java Sockets** — with **no external web framework**. It supports basic HTTP request handling (GET and POST), adheres to core HTTP specifications, and is designed for learning and extensibility.

## 🔧 Key Features

- 🧵 **Virtual Threads**: Leverages Java’s virtual threads (`Executors.newVirtualThreadPerTaskExecutor()`) for efficient, scalable concurrency without traditional thread overhead.
- 🧱 **Socket-Based Architecture**: Built directly on Java's `ServerSocket` and `Socket` — no external frameworks like Spring or Netty.
- 🔄 **HTTP Request Parsing**: Custom parsing logic for:
    - HTTP methods (GET, POST)
    - Request path and query parameters
    - Common headers like `Content-Type`, `User-Agent`, etc.
- 📜 **Specification Reference**: Parsing logic conforms to the [RFC 9112](https://www.rfc-editor.org/rfc/rfc9112) specification from the IETF (HTTP/1.1).

## 🗂️ Project Structure

```
src/
 └── main/
      └── java/
          └── org/priyanshu/httpserver/
              ├── HttpServer.java              # Entry point (loads config, starts server)
              ├── ServerListener.java          # Accepts connections, runs workers on virtual threads
              ├── HttpConnectionWorker.java    # Handles one connection lifecycle
              └── parser/
                   └── HttpRequestParser.java  # Parses raw HTTP requests
 └── resources/
      └── http.json                            # Config file (port, webroot)
```
## 🌐 Example Requests

**GET Request Example**:
```
GET /hello?name=Bablu HTTP/1.1
Host: localhost:8080
User-Agent: curl/7.68.0
```

**POST Request Example**:
```
POST /submit HTTP/1.1
Host: localhost:8080
Content-Type: application/x-www-form-urlencoded
Content-Length: 13

name=Bablu+Bhai
```

## 📘 RFC Reference

This server adheres to [RFC 9112 - HTTP/1.1](https://www.rfc-editor.org/rfc/rfc9112), especially in request parsing and header interpretation.

## ⚙️ Running the Server

Using the Maven exec plugin:

```bash
mvn clean package
mvn exec:java
```

## 📊 Performance Report (JMeter)

To evaluate the performance of this virtual-thread-based server, we used **Apache JMeter** for local testing. Below is a link to the raw `.csv` report:

➡️ [JMeter Load Test Report (CSV)](jmeter-report/results.csv)


## 📄 License

This is a personal learning project, feel free to fork and experiment.

---

**Built for learning and performance testing. No frameworks. No fluff. Just pure sockets and threads.**