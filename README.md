
```
idrp-backend
├─ .mvn
│  └─ wrapper
│     └─ maven-wrapper.properties
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ idrp
   │  │        └─ backend
   │  │           ├─ config
   │  │           ├─ controller
   │  │           │  ├─ BoardMemberController.java
   │  │           │  ├─ ContactController.java
   │  │           │  ├─ EventController.java
   │  │           │  ├─ EventRegistrationController.java
   │  │           │  ├─ MentorController.java
   │  │           │  ├─ PartnerController.java
   │  │           │  ├─ ProgramApplicationController.java
   │  │           │  ├─ ProgramController.java
   │  │           │  ├─ ResourceController.java
   │  │           │  ├─ StartupController.java
   │  │           │  └─ TeamMemberController.java
   │  │           ├─ dto
   │  │           │  ├─ boardmember
   │  │           │  │  ├─ BoardMemberRequestDto.java
   │  │           │  │  └─ BoardMemberResponseDto.java
   │  │           │  ├─ common
   │  │           │  │  └─ ApiResponse.java
   │  │           │  ├─ contact
   │  │           │  │  ├─ ContactRequestDto.java
   │  │           │  │  └─ ContactResponseDto.java
   │  │           │  ├─ event
   │  │           │  │  ├─ EventRequestDto.java
   │  │           │  │  └─ EventResponseDto.java
   │  │           │  ├─ eventregistration
   │  │           │  │  ├─ EventRegistrationRequestDto.java
   │  │           │  │  └─ EventRegistrationResponseDto.java
   │  │           │  ├─ mentor
   │  │           │  │  ├─ MentorRequestDto.java
   │  │           │  │  └─ MentorResponseDto.java
   │  │           │  ├─ partner
   │  │           │  │  ├─ PartnerRequestDto.java
   │  │           │  │  └─ PartnerResponseDto.java
   │  │           │  ├─ program
   │  │           │  │  ├─ ProgramRequestDto.java
   │  │           │  │  └─ ProgramResponseDto.java
   │  │           │  ├─ programapplication
   │  │           │  │  ├─ ProgramApplicationRequestDto.java
   │  │           │  │  └─ ProgramApplicationResponseDto.java
   │  │           │  ├─ resource
   │  │           │  │  ├─ ResourceRequestDto.java
   │  │           │  │  └─ ResourceResponseDto.java
   │  │           │  ├─ startup
   │  │           │  │  ├─ StartupRequestDto.java
   │  │           │  │  └─ StartupResponseDto.java
   │  │           │  └─ teammember
   │  │           │     ├─ TeamMemberRequestDto.java
   │  │           │     └─ TeamMemberResponseDto.java
   │  │           ├─ entity
   │  │           │  ├─ BoardMember.java
   │  │           │  ├─ Contact.java
   │  │           │  ├─ Event.java
   │  │           │  ├─ EventRegistration.java
   │  │           │  ├─ Mentor.java
   │  │           │  ├─ Partner.java
   │  │           │  ├─ Program.java
   │  │           │  ├─ ProgramApplication.java
   │  │           │  ├─ Resource.java
   │  │           │  ├─ ResourceType.java
   │  │           │  ├─ Startup.java
   │  │           │  └─ TeamMember.java
   │  │           ├─ exception
   │  │           │  ├─ DuplicateResourceException.java
   │  │           │  ├─ GlobalExceptionHandler.java
   │  │           │  └─ ResourceNotFoundException.java
   │  │           ├─ IdrpBackendApplication.java
   │  │           ├─ repository
   │  │           │  ├─ BoardMemberRepository.java
   │  │           │  ├─ ContactRepository.java
   │  │           │  ├─ EventRegistrationRepository.java
   │  │           │  ├─ EventRepository.java
   │  │           │  ├─ MentorRepository.java
   │  │           │  ├─ PartnerRepository.java
   │  │           │  ├─ ProgramApplicationRepository.java
   │  │           │  ├─ ProgramRepository.java
   │  │           │  ├─ ResourceRepository.java
   │  │           │  ├─ StartupRepository.java
   │  │           │  └─ TeamMemberRepository.java
   │  │           └─ service
   │  │              ├─ BoardMemberService.java
   │  │              ├─ ContactService.java
   │  │              ├─ EventRegistrationService.java
   │  │              ├─ EventService.java
   │  │              ├─ impl
   │  │              │  ├─ BoardMemberServiceImpl.java
   │  │              │  ├─ ContactServiceImpl.java
   │  │              │  ├─ EventRegistrationServiceImpl.java
   │  │              │  ├─ EventServiceImpl.java
   │  │              │  ├─ MentorServiceImpl.java
   │  │              │  ├─ PartnerServiceImpl.java
   │  │              │  ├─ ProgramApplicationServiceImpl.java
   │  │              │  ├─ ProgramServiceImpl.java
   │  │              │  ├─ ResourceServiceImpl.java
   │  │              │  ├─ StartupServiceImpl.java
   │  │              │  └─ TeamMemberServiceImpl.java
   │  │              ├─ MentorService.java
   │  │              ├─ PartnerService.java
   │  │              ├─ ProgramApplicationService.java
   │  │              ├─ ProgramService.java
   │  │              ├─ ResourceService.java
   │  │              ├─ StartupService.java
   │  │              └─ TeamMemberService.java
   │  └─ resources
   │     ├─ application.properties
   │     ├─ static
   │     └─ templates
   └─ test
      └─ java
         └─ com
            └─ idrp
               └─ backend
                  └─ IdrpBackendApplicationTests.java

```