USE master
GO
alter database [Quiz_System] set single_user with rollback immediate
if exists (select * from sysdatabases where name='Quiz_System') drop database Quiz_System
GO

CREATE DATABASE Quiz_System
GO

USE [Quiz_System]
GO

-------------------------------------------
CREATE TABLE dbo.[User](
	userId		int				NOT NULL identity(1,1) PRIMARY KEY,
	userName	nvarchar(63)	NOT NULL,
	[password]	nvarchar(255)	NOT NULL,
	roleId		int				NOT NULL,
	profilePic	nvarchar(255),
	userMail	nvarchar(255),
	gender		bit, 
	userMobile	nchar(10),
	[status]	bit,
)
-------------------------------------------
CREATE TABLE dbo.[Subject](
	subjectId		int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectName		nvarchar(255)	NOT NULL,
	cateId			int				NOT NULL,
	[description]	nvarchar(1023)	NOT NULL,
	thumbnail		nvarchar(255),
	userId			int				NOT NULL,
	featuredSubject bit,
	[status]		bit,
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
)
-------------------------------------------
CREATE TABLE dbo.[Dimension](
	dimensionId		int				NOT NULL identity(1,1) PRIMARY KEY,
	subjectId		int				NOT NULL,
	dimensionTypeId int				NOT NULL,
	dimensionName	nvarchar(255)	NOT NULL,
	[description]	nvarchar(511)	NOT NULL,
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
	--type?
	[status]	bit,
)
-------------------------------------------
CREATE TABLE dbo.[AnswerDetail](
	answerId		int				NOT NULL identity(1,1) PRIMARY KEY,
	questionId		int				NOT NULL,
	answerContent	nvarchar(1023)	NOT NULL,
	isCorrect		bit				NOT NULL,
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
	[testTypeId]	int				NOT NULL,
	[description]	nvarchar(1023),
	numberQuestion	int				NOT NULL,
	dimensionTypeId int				NOT NULL,
	[status]		bit,
)
-------------------------------------------
CREATE TABLE dbo.[QuizQuestion](
	quizId		int	NOT NULL,
	questionId	int NOT NULL,
)
-------------------------------------------
CREATE TABLE dbo.[CustomerQuiz](
	quizTakeId	int			NOT NULL identity(1,1) PRIMARY KEY,
	quizId		int			NOT NULL,
	userId		int			NOT NULL,
	score		int,
	startedAt	datetime	NOT NULL,
	--content?
	[status]	bit,
)
-------------------------------------------
CREATE TABLE dbo.[TakeAnswer](
	takeAnswerId	int	NOT NULL identity(1,1) PRIMARY KEY,
	quizTakeId		int	NOT NULL,
	questionId		int	NOT NULL,
	answerId		int	NOT NULL,
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
)
-------------------------------------------
CREATE TABLE dbo.[Blog](
	blogId		int				NOT NULL identity(1,1) PRIMARY KEY,
	blogTitle	nvarchar(255),
	created		datetime		NOT NULL,
	lastEdited	datetime		NOT NULL,
	author		int				NOT NULL, --User id
	detail		nvarchar(2047)	NOT NULL,
	postCateId	int				NOT NULL,
	thumbnail	nvarchar(255),
)
-------------------------------------------
CREATE TABLE dbo.[Slider](
	sliderId	int				NOT NULL identity(1,1) PRIMARY KEY,
	sliderTitle nvarchar(255)	NOT NULL,
	[image]		nvarchar(255),
	[link]		nvarchar(255),
	note		nvarchar(255),
	status		bit,
)
-------------------------------------------
CREATE TABLE dbo.[UserRole](
	userRoleId		int	NOT NULL identity(1,1) PRIMARY KEY,
	userRoleName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[PostCate](
	postCateId		int	NOT NULL identity(1,1) PRIMARY KEY,
	postCateName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[SubjectCate](
	subjectCateId		int	NOT NULL identity(1,1) PRIMARY KEY,
	subjectCateName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[TestType](
	testTypeId		int	NOT NULL identity(1,1) PRIMARY KEY,
	testTypeName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[QuizLevel](
	quizLevelId		int	NOT NULL identity(1,1) PRIMARY KEY,
	quizLevelName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[LessonType](
	lessonTypeId	int	NOT NULL identity(1,1) PRIMARY KEY,
	lessonTypeName	nvarchar(63) NOT NULL
)
-------------------------------------------
CREATE TABLE dbo.[DimensionType](
	dimensionTypeId		int	NOT NULL identity(1,1) PRIMARY KEY,
	dimensionTypeName	nvarchar(63) NOT NULL
)