# **WID**
**What I Did** or **What I'll Do**

# **Request - Response Table**
|URL|Method|Function|
|:---:|:---:|:---:|
|/quest/|POST|Create Quest|
|/quest/{qno}|GET|Read Quest|
|/quest/list|GET|Read All Quests|
|/quest/{qno}|PUT|Update Quest|
|/quest/{qno}|DELETE|Delete Quest|
|/record/|POST|Create Record|
|/record/{rno}|GET|Read Record|
|/record/list|GET|Read All Records|
|/record/{rno}|PUT|Update Record|
|/record/{rno}|DELETE|Delete Record|

# **History**
**2023.02.05**<br>
Build a Quest CRUD function
- Quest: Something to do or a plan
- Create DB and Build Entity
  - qno(entity number)
  - user
  - title
  - due date
  - finished
- Build Repository
  1. Execute CRUD method from inherited JpaRepository which is Spring API
- Build Service object
  1. DTO passed from Controller is converted to Entity through injected Mapper
  2. Execute CURD method from injected Repository with converted Entity
- Build Controller
   1. execute CURD method from injected service object with received DTO from request
- Build DTO
  - qno(DTO number)
  - user
  - title
  - dueDate
  - finished

Build Quest CRUD function test code
  - Build Repository, Service object, and Controller test code
    1. Execute Repository, Service, and Controller's CURD method to test from injected Repository, Service, and Controller, with creating arbitrary DTO, Entity, or using object existing

**2023.02.06**<br>
Build a Record CRUD function
- Record: What you did in the past, how you spent your time
- Create DB and Build Entity
  - rno(entity number)
  - user
  - title
  - date
  - start(time)
  - finish(time)
- Build Repository
  1. Execute CRUD method from inherited JpaRepository which is Spring API
- Build Service object
  1. DTO passed from Controller is converted to Entity through injected Mapper
  2. Execute CURD method from injected Repository with converted Entity
- Build Controller
  1. execute CURD method from injected service object with received DTO from request
- Build DTO
  - rno(DTO number)
  - user
  - title
  - date
  - start(time)
  - finish(time)

Build Record CRUD function test code
  - Build Repository, Service object, and Controller test code
  1. Execute Repository, Service, and Controller's CURD method to test from injected Repository, Service, and Controller, with creating arbitrary DTO, Entity, or using object existing