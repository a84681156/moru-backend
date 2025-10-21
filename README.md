# 폴더더구조 
moru/                          
├── client/
├── .mvn/
├── src/
│   └── main/
│       ├── java/com/project/moru/
│       │   ├── MoruApplication.java      
│       │   ├── domain/                   DB 매핑 객체 (JPA Entity, MyBatis DTO)
│       │   ├── repository/               JPA Repository
│       │   ├── mapper/                   MyBatis Mapper
│       │   ├── service/                  service
│       │   └── controller/               controller
│       └── resources/
│           ├── application.properties    DB 및 설정
│           ├── mapper/                   MyBatis XML
│           └── static/                   프론트엔드 빌드 파일 배포 위치 - 백엔드 Maven 빌드 설정 (통합 빌드 스크립트 포함) 해야함
├── target
├── pom.xml
└── ...