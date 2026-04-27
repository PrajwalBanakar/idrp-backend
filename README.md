
```
idrp-backend
├─ .mvn
│  └─ wrapper
│     └─ maven-wrapper.properties
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ README.md
├─ src
│  ├─ main
│  │  ├─ java
│  │  │  └─ com
│  │  │     └─ idrp
│  │  │        └─ backend
│  │  │           ├─ config
│  │  │           │  ├─ JwtAuthenticationFilter.java
│  │  │           │  ├─ SecurityConfig.java
│  │  │           │  └─ WebConfig.java
│  │  │           ├─ controller
│  │  │           │  ├─ AdminController.java
│  │  │           │  ├─ AuthController.java
│  │  │           │  ├─ BoardMemberController.java
│  │  │           │  ├─ ContactController.java
│  │  │           │  ├─ CourseRegistrationController.java
│  │  │           │  ├─ EventController.java
│  │  │           │  ├─ EventRegistrationController.java
│  │  │           │  ├─ FileController.java
│  │  │           │  ├─ MentorController.java
│  │  │           │  ├─ PartnerController.java
│  │  │           │  ├─ ProgramApplicationController.java
│  │  │           │  ├─ ProgramController.java
│  │  │           │  ├─ ResourceController.java
│  │  │           │  ├─ StartupApplicationController.java
│  │  │           │  ├─ StartupController.java
│  │  │           │  └─ TeamMemberController.java
│  │  │           ├─ dto
│  │  │           │  ├─ admin
│  │  │           │  │  ├─ AdminRequestDto.java
│  │  │           │  │  └─ AdminResponseDto.java
│  │  │           │  ├─ auth
│  │  │           │  │  ├─ AdminLoginRequestDto.java
│  │  │           │  │  ├─ AuthResponseDto.java
│  │  │           │  │  ├─ CreateAdminRequestDto.java
│  │  │           │  │  └─ RefreshTokenRequestDto.java
│  │  │           │  ├─ boardmember
│  │  │           │  │  ├─ BoardMemberRequestDto.java
│  │  │           │  │  └─ BoardMemberResponseDto.java
│  │  │           │  ├─ common
│  │  │           │  │  └─ ApiResponse.java
│  │  │           │  ├─ contact
│  │  │           │  │  ├─ ContactRequestDto.java
│  │  │           │  │  └─ ContactResponseDto.java
│  │  │           │  ├─ courseregistration
│  │  │           │  │  ├─ CourseRegistrationRequestDto.java
│  │  │           │  │  └─ CourseRegistrationResponseDto.java
│  │  │           │  ├─ event
│  │  │           │  │  ├─ EventRequestDto.java
│  │  │           │  │  └─ EventResponseDto.java
│  │  │           │  ├─ eventregistration
│  │  │           │  │  ├─ EventRegistrationRequestDto.java
│  │  │           │  │  └─ EventRegistrationResponseDto.java
│  │  │           │  ├─ file
│  │  │           │  │  └─ FileUploadResponseDto.java
│  │  │           │  ├─ mentor
│  │  │           │  │  ├─ MentorRequestDto.java
│  │  │           │  │  └─ MentorResponseDto.java
│  │  │           │  ├─ partner
│  │  │           │  │  ├─ PartnerRequestDto.java
│  │  │           │  │  └─ PartnerResponseDto.java
│  │  │           │  ├─ program
│  │  │           │  │  ├─ ProgramRequestDto.java
│  │  │           │  │  └─ ProgramResponseDto.java
│  │  │           │  ├─ programapplication
│  │  │           │  │  ├─ ProgramApplicationRequestDto.java
│  │  │           │  │  └─ ProgramApplicationResponseDto.java
│  │  │           │  ├─ resource
│  │  │           │  │  ├─ ResourceRequestDto.java
│  │  │           │  │  └─ ResourceResponseDto.java
│  │  │           │  ├─ startup
│  │  │           │  │  ├─ StartupRequestDto.java
│  │  │           │  │  └─ StartupResponseDto.java
│  │  │           │  ├─ startupapplication
│  │  │           │  │  ├─ StartupApplicationRequestDto.java
│  │  │           │  │  └─ StartupApplicationResponseDto.java
│  │  │           │  └─ teammember
│  │  │           │     ├─ TeamMemberRequestDto.java
│  │  │           │     └─ TeamMemberResponseDto.java
│  │  │           ├─ entity
│  │  │           │  ├─ Admin.java
│  │  │           │  ├─ AdminRole.java
│  │  │           │  ├─ BoardMember.java
│  │  │           │  ├─ Contact.java
│  │  │           │  ├─ CourseRegistration.java
│  │  │           │  ├─ CourseRegistrationStatus.java
│  │  │           │  ├─ CourseType.java
│  │  │           │  ├─ Event.java
│  │  │           │  ├─ EventRegistration.java
│  │  │           │  ├─ Mentor.java
│  │  │           │  ├─ Partner.java
│  │  │           │  ├─ Program.java
│  │  │           │  ├─ ProgramApplication.java
│  │  │           │  ├─ RefreshToken.java
│  │  │           │  ├─ Resource.java
│  │  │           │  ├─ ResourceType.java
│  │  │           │  ├─ Startup.java
│  │  │           │  ├─ StartupApplication.java
│  │  │           │  ├─ StartupApplicationStatus.java
│  │  │           │  └─ TeamMember.java
│  │  │           ├─ exception
│  │  │           │  ├─ DuplicateResourceException.java
│  │  │           │  ├─ GlobalExceptionHandler.java
│  │  │           │  ├─ ResourceNotFoundException.java
│  │  │           │  ├─ TokenExpiredException.java
│  │  │           │  └─ TokenRevokedException.java
│  │  │           ├─ IdrpBackendApplication.java
│  │  │           ├─ repository
│  │  │           │  ├─ AdminRepository.java
│  │  │           │  ├─ BoardMemberRepository.java
│  │  │           │  ├─ ContactRepository.java
│  │  │           │  ├─ CourseRegistrationRepository.java
│  │  │           │  ├─ EventRegistrationRepository.java
│  │  │           │  ├─ EventRepository.java
│  │  │           │  ├─ MentorRepository.java
│  │  │           │  ├─ PartnerRepository.java
│  │  │           │  ├─ ProgramApplicationRepository.java
│  │  │           │  ├─ ProgramRepository.java
│  │  │           │  ├─ RefreshTokenRepository.java
│  │  │           │  ├─ ResourceRepository.java
│  │  │           │  ├─ StartupApplicationRepository.java
│  │  │           │  ├─ StartupRepository.java
│  │  │           │  └─ TeamMemberRepository.java
│  │  │           └─ service
│  │  │              ├─ AdminService.java
│  │  │              ├─ AuthService.java
│  │  │              ├─ BoardMemberService.java
│  │  │              ├─ ContactService.java
│  │  │              ├─ CourseRegistrationService.java
│  │  │              ├─ CustomAdminDetailsService.java
│  │  │              ├─ EventRegistrationService.java
│  │  │              ├─ EventService.java
│  │  │              ├─ FileStorageService.java
│  │  │              ├─ impl
│  │  │              │  ├─ AdminServiceImpl.java
│  │  │              │  ├─ AuthServiceImpl.java
│  │  │              │  ├─ BoardMemberServiceImpl.java
│  │  │              │  ├─ ContactServiceImpl.java
│  │  │              │  ├─ CourseRegistrationServiceImpl.java
│  │  │              │  ├─ EventRegistrationServiceImpl.java
│  │  │              │  ├─ EventServiceImpl.java
│  │  │              │  ├─ FileStorageServiceImpl.java
│  │  │              │  ├─ JwtServiceImpl.java
│  │  │              │  ├─ MentorServiceImpl.java
│  │  │              │  ├─ PartnerServiceImpl.java
│  │  │              │  ├─ ProgramApplicationServiceImpl.java
│  │  │              │  ├─ ProgramServiceImpl.java
│  │  │              │  ├─ RefreshTokenServiceImpl.java
│  │  │              │  ├─ ResourceServiceImpl.java
│  │  │              │  ├─ StartupApplicationServiceImpl.java
│  │  │              │  ├─ StartupServiceImpl.java
│  │  │              │  └─ TeamMemberServiceImpl.java
│  │  │              ├─ JwtService.java
│  │  │              ├─ MentorService.java
│  │  │              ├─ PartnerService.java
│  │  │              ├─ ProgramApplicationService.java
│  │  │              ├─ ProgramService.java
│  │  │              ├─ RefreshTokenService.java
│  │  │              ├─ ResourceService.java
│  │  │              ├─ StartupApplicationService.java
│  │  │              ├─ StartupService.java
│  │  │              └─ TeamMemberService.java
│  │  └─ resources
│  │     ├─ static
│  │     └─ templates
│  └─ test
│     └─ java
│        └─ com
│           └─ idrp
│              └─ backend
│                 └─ IdrpBackendApplicationTests.java
└─ uploads

```