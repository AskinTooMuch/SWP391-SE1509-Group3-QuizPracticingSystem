USE master
GO

if exists (select * from sysdatabases where name='QuizSystem') drop database QuizSystem
GO

CREATE DATABASE QuizSystem
GO

USE [QuizSystem]
GO
---------------Create Table----------------
CREATE TABLE dbo.[UserRole](
	userRoleId		int	NOT NULL identity(1,1) PRIMARY KEY,
	userRoleName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[PostCate](
	postCateId		int	NOT NULL identity(1,1) PRIMARY KEY,
	postCateName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[SubjectCate](
	subjectCateId		int	NOT NULL identity(1,1) PRIMARY KEY,
	subjectCateName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[TestType](
	testTypeId		int	NOT NULL identity(1,1) PRIMARY KEY,
	testTypeName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[QuizLevel](
	quizLevelId		int	NOT NULL identity(1,1) PRIMARY KEY,
	quizLevelName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[LessonType](
	lessonTypeId	int	NOT NULL identity(1,1) PRIMARY KEY,
	lessonTypeName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[DimensionType](
	dimensionTypeId		int	NOT NULL identity(1,1) PRIMARY KEY,
	dimensionTypeName	nvarchar(63) NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[User](
	userId		int				NOT NULL identity(1,1) PRIMARY KEY,
	userName	nvarchar(63)	NOT NULL,
	[password]	nvarchar(255)	NOT NULL,
	roleId		int				NOT NULL,
	profilePic	nvarchar(255),
	userMail	nvarchar(255)	UNIQUE,
	gender		bit, 
	userMobile	nchar(10)		UNIQUE,
	[status]	bit,
	FOREIGN KEY (roleId) REFERENCES dbo.[UserRole](userRoleId),
)
-------------------------------------------
CREATE TABLE dbo.[Subject](
	subjectId		int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectName		nvarchar(255)	NOT NULL,
	cateId			int				NOT NULL,
	[description]	nvarchar(1023)	NOT NULL,
	thumbnail		nvarchar(255),
	featuredSubject bit,
	[status]		bit,
	FOREIGN KEY (cateId) REFERENCES dbo.[SubjectCate](subjectCateId),
)
-------------------------------------------
CREATE TABLE dbo.[SubjectExpert](
	subjectId		int,
	userId			int,
	[status]		bit,
	FOREIGN KEY (subjectId) REFERENCES dbo.[Subject](subjectId),
	FOREIGN KEY (userId)	REFERENCES dbo.[User](userId),
)
-------------------------------------------
CREATE TABLE dbo.[Lesson](
	lessonId		int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectId		int				NOT NULL,
	lessonName		nvarchar(255)	NOT NULL,
	lessonOrder		int				NOT NULL,
	lessonTypeId	int				NOT NULL,
	--lessonTopic?
	videoLink		nvarchar(255),
	content			nvarchar(1024),
	[status]		bit,
	FOREIGN KEY (subjectId)		REFERENCES dbo.[Subject](subjectId),
	FOREIGN KEY (lessonTypeId)	REFERENCES dbo.[LessonType](lessonTypeId),
)
-------------------------------------------
CREATE TABLE dbo.[Dimension](
	dimensionId		int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectId		int				NOT NULL,
	dimensionTypeId int				NOT NULL,
	dimensionName	nvarchar(255)	NOT NULL,
	[description]	nvarchar(511)	NOT NULL,
	[status]		bit,
	FOREIGN KEY (subjectId)			REFERENCES dbo.[Subject](subjectId),
	FOREIGN KEY (dimensionTypeId)	REFERENCES dbo.[DimensionType](dimensionTypeId),
)
-------------------------------------------
CREATE TABLE dbo.[PricePackage](
	packId		int				NOT NULL identity(1,1) PRIMARY KEY,
	packName	nvarchar(255)	NOT NULL,
	subjectId	int				NOT NULL,
	duration	int				NOT NULL,
	listPrice	money			NOT NULL,
	salePrice	money,
	[status]	bit,
	FOREIGN KEY	(subjectId)		REFERENCES dbo.[Subject](subjectId),
)
-------------------------------------------
CREATE TABLE dbo.[Question](
	questionId	int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectId	int				NOT NULL,
	dimensionId	int				NOT NULL,
	lessonId	int				NOT NULL,
	content		nvarchar(1023)	NOT NULL,
	media		nvarchar(255),
	explanation	nvarchar(1023),
	[status]	bit,
	FOREIGN KEY (subjectId)		REFERENCES dbo.[Subject](subjectId),
	FOREIGN KEY (dimensionId)	REFERENCES dbo.[Dimension](dimensionId),
	FOREIGN KEY (lessonId)		REFERENCES dbo.[Lesson](lessonId),
)
-------------------------------------------
CREATE TABLE dbo.[Answer](
	answerId		int				NOT NULL identity(1,1) PRIMARY KEY,
	questionId		int				NOT NULL,
	answerContent	nvarchar(1023)	NOT NULL,
	isCorrect		bit				NOT NULL,
	[status]		bit,
	FOREIGN KEY (questionId) REFERENCES dbo.[Question](questionId),
)
-------------------------------------------
CREATE TABLE dbo.[Quiz](
	quizId			int				NOT NULL identity(1,1) PRIMARY KEY,
	lessonId		int				NOT NULL,
	subjectId		int				NOT NULL,
	quizName		nvarchar(255)	NOT NULL,
	quizLevelId		int				NOT NULL,
	quizDuration	int				NOT NULL, --Minutes
	passRate		int, --1-100?
	testTypeId		int				NOT NULL,
	[description]	nvarchar(1023),
	numberQuestion	int				NOT NULL,
	dimensionTypeId int				NOT NULL,
	[status]		bit,
	FOREIGN KEY (lessonId)			REFERENCES dbo.[Lesson](lessonId),
	FOREIGN KEY (subjectId)			REFERENCES dbo.[Subject](subjectId),
	FOREIGN KEY (quizLevelId)		REFERENCES dbo.[QuizLevel](quizLevelId),
	FOREIGN KEY (testTypeId)		REFERENCES dbo.[TestType](testTypeId),
	FOREIGN KEY (dimensionTypeId)	REFERENCES dbo.[DimensionType](dimensionTypeId),
)
-------------------------------------------
CREATE TABLE dbo.[QuizQuestion](
	quizId		int	NOT NULL,
	questionId	int NOT NULL,
	[status]	bit,
	FOREIGN KEY (quizId)		REFERENCES dbo.[Quiz](quizId),
	FOREIGN KEY (questionId)	REFERENCES dbo.[Question](questionId),
)
-------------------------------------------
CREATE TABLE dbo.[CustomerQuiz](
	quizTakeId	int			NOT NULL identity(1,1) PRIMARY KEY,
	quizId		int			NOT NULL,
	userId		int			NOT NULL,
	score		int,
	startedAt	datetime	NOT NULL,
	[status]	bit,
	FOREIGN KEY (quizId) REFERENCES dbo.[Quiz](quizId),
	FOREIGN KEY (userId) REFERENCES dbo.[User](userId),
)
-------------------------------------------
CREATE TABLE dbo.[TakeAnswer](
	takeAnswerId	int	NOT NULL identity(1,1) PRIMARY KEY,
	quizTakeId		int	NOT NULL,
	questionId		int	NOT NULL,
	answerId		int	NOT NULL,
	[status]		bit,
	FOREIGN KEY (quizTakeId)	REFERENCES dbo.[CustomerQuiz](quizTakeId),
	FOREIGN KEY (questionId)	REFERENCES dbo.[Question](questionId),
	FOREIGN KEY (answerId)		REFERENCES dbo.[Answer](answerId),
)
-------------------------------------------
CREATE TABLE dbo.[Registration](
	regId			int				NOT NULL identity(1,1) PRIMARY KEY,
	userId			int				NOT NULL,
	regTime			datetime		NOT NULL,
	packId			int				NOT NULL,
	cost			money,
	validFrom		datetime		NOT NULL,
	validTo			datetime		NOT NULL,
	lastUpdatedBy	int				NOT NULL, --Last updated by
	note			nvarchar(255),
	[status]		bit,
	FOREIGN KEY (userId) REFERENCES dbo.[User](userId),
	FOREIGN KEY (packId) REFERENCES dbo.[PricePackage](packId),
	FOREIGN KEY (lastUpdatedBy) REFERENCES dbo.[User](userId),
)
-------------------------------------------
CREATE TABLE dbo.[Blog](
	blogId		int				NOT NULL identity(1,1) PRIMARY KEY,
	blogTitle	nvarchar(255),
	created		datetime		NOT NULL,
	lastEdited	datetime		NOT NULL,
	author		int				NOT NULL, --User id
	detail		nvarchar(2047)	NOT NULL,
	thumbnail	nvarchar(255),
	[status]		bit,
	FOREIGN KEY (author) REFERENCES dbo.[User](userId),
)
-------------------------------------------
CREATE TABLE dbo.[BlogCate](
	blogId	int	NOT NULL,
	postCateId	int NOT NULL,
	[status]		bit,
	FOREIGN KEY (blogId) REFERENCES dbo.[Blog](blogId),
	FOREIGN KEY (postCateId) REFERENCES dbo.[PostCate](postCateId),
)
-------------------------------------------
CREATE TABLE dbo.[Slider](
	sliderId	int				NOT NULL identity(1,1) PRIMARY KEY,
	sliderTitle nvarchar(255)	NOT NULL,
	[image]		nvarchar(255),
	[link]		nvarchar(255),
	note		nvarchar(255),
	[status]	bit,
)
----------Insert Data----------------------
----------dbo.[UserRole]-------------------
INSERT INTO dbo.UserRole(userRoleName,status) VALUES('Customer',1);
INSERT INTO dbo.UserRole(userRoleName,status) VALUES('Marketing',1);
INSERT INTO dbo.UserRole(userRoleName,status) VALUES('Sale',1);
INSERT INTO dbo.UserRole(userRoleName,status) VALUES('Expert',1);
INSERT INTO dbo.UserRole(userRoleName,status) VALUES('Admin',1);
----------dbo.[PostCate]-------------------
INSERT INTO dbo.PostCate(postCateName,status) VALUES('Tips and Tricks',1);
INSERT INTO dbo.PostCate(postCateName,status) VALUES('Review and Recommendation',1);
INSERT INTO dbo.PostCate(postCateName,status) VALUES('Casuals',1);
INSERT INTO dbo.PostCate(postCateName,status) VALUES('Rest and Relax',1);
----------dbo.[SubjectCate]----------------
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Computer Science',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Java',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('OOP-Object Oriented Programming',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('C',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('C#',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Web Design',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Digital Art',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Japanese',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('English',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Algebra',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Organic Chemistry',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Basic Economic',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('Buiseness Ethics',1);
INSERT INTO dbo.SubjectCate(subjectCateName,status) VALUES('CSS',1);
----------dbo.[TestType]-------------------
INSERT INTO dbo.TestType(testTypeName,status) VALUES('Simulation',1);
INSERT INTO dbo.TestType(testTypeName,status) VALUES('Midterm',1);
INSERT INTO dbo.TestType(testTypeName,status) VALUES('Progress Test',1);
----------dbo.[QuizLevel]------------------
INSERT INTO dbo.QuizLevel(quizLevelName,status) VALUES('Hard',1);
INSERT INTO dbo.QuizLevel(quizLevelName,status) VALUES('Medium',1);
INSERT INTO dbo.QuizLevel(quizLevelName,status) VALUES('Easy',1);
----------dbo.[LessonType]-----------------
INSERT INTO dbo.LessonType(lessonTypeName,status) VALUES('Subject-Topic',1);
INSERT INTO dbo.LessonType(lessonTypeName,status) VALUES('Lesson',1);
INSERT INTO dbo.LessonType(lessonTypeName,status) VALUES('Quiz',1);
----------dbo.[DimensionType]--------------
INSERT INTO dbo.DimensionType(dimensionTypeName,status) VALUES('Domain',1);
INSERT INTO dbo.DimensionType(dimensionTypeName,status) VALUES('Group',1);
----------dbo.[User]-----------------------
INSERT INTO dbo.[User](userName,[password],roleId,profilePic,userMail,gender,userMobile,[status]) 
				VALUES(DuongNH,1,5,'','DuongNHHE150328@fpt.edu.vn',1,'',1);
INSERT INTO dbo.[User](userName,[password],roleId,profilePic,userMail,gender,userMobile,[status]) 
				VALUES(NamDH,1,5,'','NamDHHE150519@fpt.edu.vn',1,'',1);
INSERT INTO dbo.[User](userName,[password],roleId,profilePic,userMail,gender,userMobile,[status]) 
				VALUES(TuanPA,1,5,'','TuanPAHE150543@fpt.edu.vn',1,'',1);
INSERT INTO dbo.[User](userName,[password],roleId,profilePic,userMail,gender,userMobile,[status]) 
				VALUES(ChucNV,1,5,'','ChucNVHE150618@fpt.edu.vn',1,'',1);
INSERT INTO dbo.[User](userName,[password],roleId,profilePic,userMail,gender,userMobile,[status]) 
				VALUES(TungBT,1,5,'','TungBTHE150621@fpt.edu.vn',1,'',1);
----------dbo.[Subject]--------------------

-------------------------------------------
----------dbo.[SubjectExpert]--------------

-------------------------------------------
----------dbo.[Lesson]---------------------

-------------------------------------------
----------dbo.[Dimension]------------------

-------------------------------------------
----------dbo.[PricePackage]---------------

-------------------------------------------
----------dbo.[Question]-------------------

-------------------------------------------
----------dbo.[Answer]---------------------

-------------------------------------------
----------dbo.[Quiz]-----------------------

-------------------------------------------
----------dbo.[QuizQuestion]---------------

-------------------------------------------
----------dbo.[CustomerQuiz]---------------

-------------------------------------------
----------dbo.[TakeAnswer]-----------------

-------------------------------------------
----------dbo.[Registration]---------------

-------------------------------------------
----------dbo.[Blog]-----------------------

-------------------------------------------
----------dbo.[BlogCate]-------------------

-------------------------------------------
----------dbo.[Slider]---------------------

-------------------------------------------