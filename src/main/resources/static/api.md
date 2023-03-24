FORMAT: 1A
HOST: http://ec2-43-201-189-36.ap-northeast-2.compute.amazonaws.com:8080

# Numble - 네이버 MYBOX

네이버 MYBOX 클론 코딩 API 정의서

# Group Users

유저 관련 API

## 유저 기본 정보 조회 [GET /users/{userId}]

+ Parameters
    + userId (required, integer) - 유저 id.

+ Response 200 (application/json)
    + Attributes (object)
        + userId: 123 (number, required) - user table key
        + username: numbleuser (string) - 유저 Id
        + userPicture: https://cdn.icon-icons.com/icons2/2760/PNG/512/profile_icon_176363.png (string) - 프로필 사진 url
        + myboxUsage (object)
            + totalUsage: 123456 (number) 총 사용량
            + usedUsage: 12345 (number) 남은 사용량

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
        + profileImage: 25 (string) - 프로필 이미지
    + Body
      {
      "username": "numbleuser",
      "password": 1415203908,
      "profileImage":"https://cdn.icon-icons.com/icons2/2760/PNG/512/profile_icon_176363.png"
      }

+ Response 201 (application/json)
    + Body
      {
      "id": 123,
      "username": "numbleuser"
      }

+ Response 400 (application/json)
    + Body
      {
      message : "이미 존재하는 id입니다.",
      "status": 400
      }

# Group File

* 업로드
* 다운로드
* 삭제

## 파일 업로드 [POST /files/multi/upload]

multi file upload

+ Request (multipart/form-data)
    + Attributes (object)
        - file: `value1` (string, required) - file
    + Body
      {
      "file": "value1"
      }

+ Response 201 (application/json)


+ Response 400 (application/json)
    + Body
      {
      message : "업로드할 공간이 부족합니다.",
      "status": 400
      }

## 파일 다운로드 [GET /files/download/{fileId}]

+ Parameters
    + fileId (number, required) - 파일 id


+ Response 201 (application/json)


+ Response 400 (application/json)
    + Body
      {
      message : "다른 유저의 파일은 다운받을 수 없습니다.",
      "status": 400
      }

## 파일 삭제 [DELETE /files/download/{fileId}]

+ Parameters
    + fileId (number, required) - 파일 id


+ Response 204 (application/json)


+ Response 400 (application/json)
    + Body
      {
      message : "다른 유저의 파일은 삭제할 수 없습니다.",
      "status": 400
      }

# Group Folder
