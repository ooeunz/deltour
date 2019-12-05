# Deltour
Deltour is a chatbot my second(Mark-2) project.  
Mark-2 can dialog with user about travel and catch what you want travel style.
Finally Mark-1 will reccomand you travel area.
\
[]()
## Mark-2
Mark-2 is my second chatbot project. It is compare The Iron Man's second model.
\
[]()
## Development in progress
if you have any suggestions, do not hesitate contact me. => yuns994@gmail.com
\
[]()
---
\
\
[]()
### Using Technology
1. Dialogflow (Google NLP model)
2. Spring Boot (chatbot server)
3. MongoDB
4. AWS (EC2, RDS, S3)
5. (Eureka) // if i have a chance
6. (Elastic Search)
\
[]()
## API Description
null
\
[]()
## Usage Video
null
\
\
[]()

---
\
\
[]()
## Architecture Style
> Micro Service Architecture
* Microservices are a software development technique—a variant of the service-oriented architecture (SOA) architectural style that structures an application as a collection of loosely coupled services. In a microservices architecture, services are fine-grained and the protocols are lightweight.

![image](./readmeImg/microservices.png)
\
\
[]()

---
\
\
[]()
## Design Pattern
> Single Pattern
* In software engineering, the singleton pattern is a software design pattern that restricts the instantiation of a class to one "single" instance. This is useful when exactly one object is needed to coordinate actions across the system. The term comes from the mathematical concept of a singleton.

![image](./readmeImg/singleton.jpg)
\
\
[]()

---
\
\
[]()
## Foldering
null
\
\
[]()

---
\
\
[]()
## Usage Dependency

```gragle
dependencies {
    compile 'com.google.cloud:google-cloud-dialogflow:0.93.0-alpha'
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'
    implementation 'org.springframework.boot:spring-boot-starter-data-rest'
}
```
> It used dialogflow as an NLP model only for analyzing intents.
> And other business logic and data used spring and NoSQL.

\
[]()
```gragle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
  }
```
> It have login function using spring security. So permissions can be distinguished according to the user

\
[]()

---
\
\
[]()
## Core Technology
**Dialogflow**
> SOLscript는 챗봇 soly와의 대화를 통해 유저의 취향을 분석한 후 유저에게 맞춤형 구독 서비스를 추천해준다. 취향 분석은 Price, Category, Feel 총 3가지의 기준으로 분석하며, 유저가 정형화되지 않은 답변을 하더라도 챗봇이 해당 발화의 Entity를 추출하여 준비된 키워드에 맵핑시킨다. 또한 사용자의 구독 서비스 현황이나, 자주 사용하는 구독 서비스 현황 등에 대한 질문을 Database접근 및 내부 로직을 통해 답변할 수 있다. 
[참고자료: Dialogflow Reference](https://cloud.google.com/dialogflow/docs/reference/rest/v2/projects.agent.sessions/detectIntent)

\
[]()
**Security**
> 신한은행 및 카드 API를 사용해서 카드를 등록해서 간편하게 구독 서비스를 관리할 수 있도록 기능을 제공하였고, 유저의 카드 사용내역을 조회 및 파싱하여 사용자의 구독 서비스 사용패턴을 분석한 후 제공하였다.

\
[]()
---