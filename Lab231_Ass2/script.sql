USE [master]
GO
/****** Object:  Database [LAB231_ASS2]    Script Date: 6/11/2021 8:11:19 PM ******/
CREATE DATABASE [LAB231_ASS2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LAB231_ASS2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\LAB231_ASS2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LAB231_ASS2_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\LAB231_ASS2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [LAB231_ASS2] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LAB231_ASS2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LAB231_ASS2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET ARITHABORT OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LAB231_ASS2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LAB231_ASS2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LAB231_ASS2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LAB231_ASS2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET RECOVERY FULL 
GO
ALTER DATABASE [LAB231_ASS2] SET  MULTI_USER 
GO
ALTER DATABASE [LAB231_ASS2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LAB231_ASS2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LAB231_ASS2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LAB231_ASS2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LAB231_ASS2] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [LAB231_ASS2] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'LAB231_ASS2', N'ON'
GO
ALTER DATABASE [LAB231_ASS2] SET QUERY_STORE = OFF
GO
USE [LAB231_ASS2]
GO
/****** Object:  Table [dbo].[Active]    Script Date: 6/11/2021 8:11:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Active](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[action] [nvarchar](250) NOT NULL,
	[byUserId] [int] NOT NULL,
	[date] [date] NOT NULL,
	[name] [varchar](50) NULL,
	[email] [varchar](50) NULL,
 CONSTRAINT [PK_Active] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 6/11/2021 8:11:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupUser]    Script Date: 6/11/2021 8:11:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupUser](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[groupId] [int] NOT NULL,
 CONSTRAINT [PK_GroupUser] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 6/11/2021 8:11:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Promotion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NOT NULL,
	[rank] [int] NOT NULL,
 CONSTRAINT [PK_Promotion] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 6/11/2021 8:11:19 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[userId] [nchar](200) NOT NULL,
	[password] [nchar](200) NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[role] [nchar](10) NOT NULL,
	[email] [nvarchar](250) NULL,
	[phone] [nchar](10) NULL,
	[avatar] [nvarchar](250) NULL,
	[status] [nchar](10) NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Active] ADD  CONSTRAINT [DF_Active_Date]  DEFAULT (getdate()) FOR [date]
GO
ALTER TABLE [dbo].[Active]  WITH CHECK ADD  CONSTRAINT [FK_Active_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Active] CHECK CONSTRAINT [FK_Active_User]
GO
ALTER TABLE [dbo].[GroupUser]  WITH CHECK ADD  CONSTRAINT [FK_GroupUser_Group] FOREIGN KEY([groupId])
REFERENCES [dbo].[Group] ([id])
GO
ALTER TABLE [dbo].[GroupUser] CHECK CONSTRAINT [FK_GroupUser_Group]
GO
ALTER TABLE [dbo].[GroupUser]  WITH CHECK ADD  CONSTRAINT [FK_GroupUser_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[GroupUser] CHECK CONSTRAINT [FK_GroupUser_User]
GO
ALTER TABLE [dbo].[Promotion]  WITH CHECK ADD  CONSTRAINT [FK_Promotion_User] FOREIGN KEY([userId])
REFERENCES [dbo].[User] ([id])
GO
ALTER TABLE [dbo].[Promotion] CHECK CONSTRAINT [FK_Promotion_User]
GO
USE [master]
GO
ALTER DATABASE [LAB231_ASS2] SET  READ_WRITE 
GO
