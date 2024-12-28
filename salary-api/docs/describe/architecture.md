### 架构设计
#### 1. 系统架构



salary-api/
│  pom.xml
│  
├─docs
│  │  README.md
│  │  
│  ├─api
│  │      swagger.json
│  │      
│  ├─database
│  │      schema.md
│  │      
│  └─describe
│          architecture.md
│
└─src
    └─main
        ├─java
        │  └─com
        │      └─xuhao
        │          └─salary
        │              │  SalaryApiApplication.java
        │              │
        │              ├─application
        │              │  ├─assembler
        │              │  │      EmployeeAssembler.java
        │              │  │      SalaryAssembler.java
        │              │  │
        │              │  ├─dto
        │              │  │  ├─common
        │              │  │  │      ApiResponse.java
        │              │  │  │
        │              │  │  ├─request
        │              │  │  │  ├─auth
        │              │  │  │  │      LoginRequest.java
        │              │  │  │  │
        │              │  │  │  ├─employee
        │              │  │  │  │      CreateEmployeeRequest.java
        │              │  │  │  │      UpdateEmployeeRequest.java
        │              │  │  │  │
        │              │  │  │  └─salary
        │              │  │  │          CalculateSalaryRequest.java
        │              │  │  │          PayrollRequest.java
        │              │  │  │
        │              │  │  └─response
        │              │  │      ├─auth
        │              │  │      │      LoginResponse.java
        │              │  │      │
        │              │  │      ├─employee
        │              │  │      │      EmployeeDetailResponse.java
        │              │  │      │      EmployeeListResponse.java
        │              │  │      │
        │              │  │      └─salary
        │              │  │              PayrollSummaryResponse.java
        │              │  │              SalaryDetailResponse.java
        │              │  │
        │              │  └─service
        │              │      ├─auth
        │              │      │  │  AuthService.java
        │              │      │  │
        │              │      │  └─impl
        │              │      │          AuthServiceImpl.java
        │              │      │
        │              │      ├─impl
        │              │      │      AuthApplicationServiceImpl.java
        │              │      │      EmployeeApplicationServiceImpl.java
        │              │      │      SalaryApplicationServiceImpl.java
        │              │      │
        │              │      └─interfaces
        │              │              AuthApplicationService.java
        │              │              EmployeeApplicationService.java
        │              │              SalaryApplicationService.java
        │              │
        │              ├─common
        │              │  ├─constant
        │              │  │      ErrorCode.java
        │              │  │      SystemConstant.java
        │              │  │
        │              │  ├─exception
        │              │  │      BusinessException.java
        │              │  │      ValidationException.java
        │              │  │
        │              │  └─util
        │              │          JwtUtil.java
        │              │          Result.java
        │              │
        │              ├─config
        │              ├─domain
        │              │  ├─event
        │              │  │      SalaryCalculatedEvent.java
        │              │  │
        │              │  ├─model
        │              │  │  ├─employee
        │              │  │  │      Employee.java
        │              │  │  │
        │              │  │  ├─salary
        │              │  │  │      Salary.java
        │              │  │  │
        │              │  │  └─system
        │              │  │          User.java
        │              │  │          UserRole.java
        │              │  │
        │              │  ├─repository
        │              │  │      EmployeeRepository.java
        │              │  │      SalaryRepository.java
        │              │  │      UserRepository.java
        │              │  │
        │              │  └─service
        │              │      ├─impl
        │              │      │      AuthDomainServiceImpl.java
        │              │      │      EmployeeDomainServiceImpl.java
        │              │      │      SalaryDomainServiceImpl.java
        │              │      │
        │              │      └─interfaces
        │              │              AuthDomainService.java
        │              │              EmployeeDomainService.java
        │              │              SalaryDomainService.java
        │              │
        │              ├─infrastructure
        │              │  ├─config
        │              │  │      JwtConfig.java
        │              │  │      MyBatisConfig.java
        │              │  │      RedisConfig.java
        │              │  │      SecurityConfig.java
        │              │  │      WebConfig.java
        │              │  │
        │              │  ├─exception
        │              │  │      BusinessException.java
        │              │  │      GlobalExceptionHandler.java
        │              │  │
        │              │  ├─persistence
        │              │  │  ├─converter
        │              │  │  │      EmployeeConverter.java
        │              │  │  │      SalaryConverter.java
        │              │  │  │
        │              │  │  ├─entity
        │              │  │  │      UserEntity.java
        │              │  │  │
        │              │  │  ├─mapper
        │              │  │  │      EmployeeMapper.java
        │              │  │  │      SalaryMapper.java
        │              │  │  │      UserMapper.java
        │              │  │  │
        │              │  │  └─repository
        │              │  │      └─impl
        │              │  │              EmployeeRepositoryImpl.java
        │              │  │              SalaryRepositoryImpl.java
        │              │  │              UserRepositoryImpl.java
        │              │  │
        │              │  ├─repository
        │              │  ├─security
        │              │  │      CustomUserDetailsService.java
        │              │  │      JwtAuthenticationFilter.java
        │              │  │      JwtTokenProvider.java
        │              │  │      UserDetailsServiceImpl.java
        │              │  │
        │              │  └─service
        │              │          AuthenticationService.java
        │              │
        │              └─interfaces
        │                  ├─controller
        │                  │      AuthController.java
        │                  │      EmployeeController.java
        │                  │      SalaryController.java
        │                  │      TestController.java
        │                  │
        │                  └─facade
        │                      ├─auth
        │                      │      AuthFacade.java
        │                      │
        │                      ├─employee
        │                      │      EmployeeFacade.java
        │                      │
        │                      └─salary
        │                              SalaryFacade.java
        │
        └─resources
            │  application-dev.yml
            │  application-prod.yml
            │  application.yml
            │
            ├─db
            │  │  init.sql
            │  │  test.sql
            │  │
            │  └─migration
            │          V1__init_schema.sql
            │          V2__init_data.sql
            │
            └─mapper
                    UserMapper.xml

                    