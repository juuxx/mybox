FORMAT: 1A
HOST: https://localhost:8080

# Numble - 네이버 MYBOX

네이버 MYBOX 클론 코딩 API 정의서

# Group Users
유저 관련 API

## 유저 기본 정보 조회 [GET /users/{id}]

+ Parameters
    + id (required, integer) - The ID of the user to retrieve.

+ Response 200 (application/json)

    + Attributes (object)
        + userId: 123 (number, required)
        + username: numbleuser (string) - 유저 Id
        + nickname: numble (string)
        + userPicture: url (string) - 프로필 사진 url
        + myboxUsage (object)
            + totalUsage: 123456 (number) 총 사용량
            + usedUsage: 123456 (number) 남은 사용량

    + Body

      {
      userId : 123,
      username : "numbleuser",
      nickname : "numble",
      userPicture : "https://cdn.icon-icons.com/icons2/2760/PNG/512/profile_icon_176363.png"
      myboxUsage : {
      totalUsage : 123456,
      usedUsage : 12345
      }

+ Response 400 (application/json)
    + Body
      {
      "message": "회원이 존재하지 않습니다.",
      "status": 400
      }


## 유저 회원 가입 [POST /users]
유저 회원 가입
user 회원가입 API


+ Request (application/json)
    + Attributes (object)
        + username: numbleuser (string, required) - 유저 ID
        + password: 1415203908 (number, required) - 비밀번호
        + nickname: numble (string, require) - 닉네임
        + profileImage: 25 (string) - 프로필 이미지
    + Body
      {
      "username": "numbleuser",
      "password": 1415203908,
      "nickname": "numble",
      "profileImage":"https://cdn.icon-icons.com/icons2/2760/PNG/512/profile_icon_176363.png"
      }

+ Response 201 (application/json)
    + Body
      {
      "id": 123,
      "name": "John Doe",
      "age": 30,
      "email": "johndoe@example.com"
      }

+ Response 400 (application/json)
    + Body
      {
      message : "이미 존재하는 id입니다."
      }


# Group File