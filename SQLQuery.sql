use master
go
create database WebStore
go
use WebStore
go

--create table

create table Category(
	IDCategory int CONSTRAINT PK_Category primary key identity,
	Name nvarchar(50) not null,
	ImageUrl nvarchar(500)
)
go

create table Product(
	IDProduct int CONSTRAINT PK_Product primary key identity,
	Name nvarchar(50) not null,
	ImageUrl nvarchar(500),
	Price float not null,
	Description nvarchar(250),
	CategoryID int CONSTRAINT FK_ProductCategory foreign key references Category(IDCategory) not null
)
go

create table [User](
	IDUser int CONSTRAINT PK_User primary key identity,
	Username nvarchar(50) not null unique,
	Password nvarchar(50) not null,
	UserType int,
	FirstName nvarchar(50),
	LastName nvarchar(50),
	Address nvarchar(50),
	ZipCode nvarchar(50),
	City nvarchar(50),
	Country nvarchar(50)
)
go

create table [Order](
	IDOrder int CONSTRAINT PK_Order primary key identity,
	PaymentType int,
	DateTime datetime,
	UserID int CONSTRAINT FK_OrderUser foreign key references [User](IDUser) not null,
	ShippingFirstName nvarchar(50),
	ShippingLastName nvarchar(50),
	ShippingAddress nvarchar(50),
	ShippingZipCode nvarchar(50),
	ShippingCity nvarchar(50),
	ShippingCountry nvarchar(50)
)
go

create table Item(
	IDItem int CONSTRAINT PK_Item primary key identity,
	OrderID int CONSTRAINT FK_ItemOrder foreign key references [Order](IDOrder) not null,
	ProductID int not null,
	Name nvarchar(50) not null,
	ImageUrl nvarchar(500),
	Description nvarchar(250),
	Price float not null,
	Quantity int
)
go


create table LoginLog(
	IDLoginLog int CONSTRAINT PK_LoginLog primary key identity,
	UserID int CONSTRAINT FK_LoginLogUser foreign key references [User](IDUser) not null,
	DateTime datetime,
	IPAddress nvarchar(50)
)
go



-- sp

create procedure selectCategories
as
begin
	select * from Category
end
go

create procedure selectProducts
	@CategoryID int
as
begin
	select * from Product where CategoryID=@CategoryID
end
go

create procedure selectProduct
	@IDProduct int
as
begin
	select * from Product where IDProduct=@IDProduct
end
go

create procedure selectCategory
	@IDCategory int
as
begin
	select * from Category where IDCategory=@IDCategory
end
go



create procedure userLogin
	@Username nvarchar(50),
	@Password nvarchar(50)
as
begin
	SELECT CASE WHEN EXISTS (
		SELECT * FROM [User] WHERE Username = @Username and  Password = @Password
	)
	THEN 1
	ELSE 0 END as 'Result'
end
go

create procedure selectUser
	@IDUser int
as
begin
	select * from [User] where IDUser=@IDUser
end
go

create procedure selectUserWithUsername
	@Username nvarchar(50)
as
begin
	select * from [User] where Username=@Username
end
go



create procedure userRegistration
	@Username nvarchar(50),
	@Password nvarchar(50),
	@FirstName nvarchar(50),
	@LastName nvarchar(50),
	@Address nvarchar(50),
	@ZipCode nvarchar(50),
	@City nvarchar(50),
	@Country nvarchar(50)
as
begin

SELECT CASE WHEN EXISTS (
	SELECT * FROM [User] WHERE Username = @Username
)
THEN 0
ELSE 1 END as 'Result'

IF not EXISTS (select * from [User] where Username=@Username)
	insert into [User] values (@Username,@Password,2,@FirstName,@LastName,@Address,@ZipCode,@City,@Country)
end

go


CREATE PROCEDURE createOrder
	@PaymentType int,
	@DateTime datetime,
	@UserID INT,
	@ShippingFirstName nvarchar(50),
	@ShippingLastName nvarchar(50),
	@ShippingAddress nvarchar(50),
	@ShippingZipCode nvarchar(50),
	@ShippingCity nvarchar(50),
	@ShippingCountry nvarchar(50),
	@Id INT OUTPUT
AS 
BEGIN 
	INSERT INTO [Order] VALUES(@PaymentType, @DateTime, @UserID, @ShippingFirstName,@ShippingLastName,@ShippingAddress,@ShippingZipCode,@ShippingCity,
	@ShippingCountry)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE createItem
	@OrderID int,
	@ProductID int,
	@Name nvarchar(50),
	@ImageUrl nvarchar(500),
	@Description nvarchar(250),
	@Price float,
	@Quantity int
AS 
BEGIN 
	INSERT INTO Item VALUES(@OrderID, @ProductID, @Name, @ImageUrl, @Description, @Price, @Quantity)
END
GO


CREATE PROCEDURE selectOrdersByUserId
	@UserID INT
AS 
BEGIN 
	select IDOrder,PaymentType,DateTime from [Order] where UserID=@UserID
END
GO

create PROCEDURE selectOrderItems
	@OrderID INT
AS 
BEGIN 
	select * from Item as i
	where OrderID = @OrderID
END
GO



create procedure selectProductsByAdmin
as
begin
	select p.IDProduct,p.Name,p.ImageUrl,p.Price,c.Name as Category
	from Product as p
	inner join Category as c
	on p.CategoryID = c.IDCategory
end
go


create procedure selectOrdersByAdmin
	@Username nvarchar(50) = NULL,
	@DateFrom  DATE = NULL,
	@DateTo    DATE = NULL
as
begin
IF (@Username IS NULL and @DateFrom IS NULL and @DateTo IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	order by IDOrder desc
ELSE IF (@DateFrom IS NULL and @DateTo IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where u.Username LIKE '%' + LTRIM(RTRIM(@Username)) + '%'
	order by IDOrder desc
ELSE IF (@Username IS NULL and @DateTo IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] > @DateFrom
	order by IDOrder desc
ELSE IF (@Username IS NULL and @DateFrom IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] < @DateTo
	order by IDOrder desc
ELSE IF (@Username IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] BETWEEN @DateFrom AND @DateTo
	order by IDOrder desc
ELSE IF (@DateTo IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] > @DateFrom
	and u.Username LIKE '%' + LTRIM(RTRIM(@Username)) + '%'
	order by IDOrder desc
ELSE IF (@DateFrom IS NULL) 
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] < @DateTo
	and u.Username LIKE '%' + LTRIM(RTRIM(@Username)) + '%'
	order by IDOrder desc
ELSE
	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where o.[DateTime] BETWEEN @DateFrom AND @DateTo
	and u.Username LIKE '%' + LTRIM(RTRIM(@Username)) + '%'
	order by IDOrder desc
end
go

create procedure selectOrdersByAdmin2
	@Username nvarchar(50)
as
begin

	select o.IDOrder,u.Username,o.PaymentType,o.[DateTime]
	from [Order] as o
	inner join [User] as u
	on o.UserID=u.IDUser
	where u.Username LIKE '%' + LTRIM(RTRIM(@Username)) + '%'
	order by IDOrder desc
end
go

create procedure createCategory
	@Name nvarchar(50),
	@ImageUrl nvarchar(500)
as 
begin 
	insert into Category values(@Name,@ImageUrl)
end
go


create procedure createProduct
	@Name nvarchar(50),
	@ImageUrl nvarchar(500),
	@Price float,
	@Description nvarchar(250),
	@CategoryID int
as 
begin 
	insert into Product values (@Name, @ImageUrl, @Price, @Description, @CategoryID)
end
go

create procedure updateCategory
	@IdCategory int,
	@Name nvarchar(50),
	@ImageUrl nvarchar(500)
as 
begin 
	update Category
	set Name = @Name, ImageUrl = @ImageUrl	
	where IdCategory = @IdCategory
end
go

create procedure updateProduct
	@IdProduct int,
	@Name nvarchar(50),
	@ImageUrl nvarchar(500),
	@Price float,
	@Description nvarchar(250),
	@CategoryID int
as 
begin 
	update Product
	set Name = @Name, ImageUrl = @ImageUrl, Price = @Price, [Description] = @Description, CategoryID = @CategoryID
	where IdProduct = @IdProduct
end
go

create procedure deleteCategory
	@IDCategory int
as 
begin 
	delete from Category where IDCategory = @IDCategory
end
go

create procedure deleteProduct
	@IdProduct int
as 
begin 
	delete from Product where IdProduct = @IdProduct
end
go


CREATE PROCEDURE createLoginLog
	@UserID INT,
	@DateTime datetime,
	@IPAddress nvarchar(50)
AS 
BEGIN 
	INSERT INTO LoginLog VALUES(@UserID, @DateTime, @IPAddress)
END
GO

CREATE PROCEDURE selectLoginLog
AS 
BEGIN 
	select ll.IDLoginLog,u.Username,ll.DateTime,ll.IPAddress from LoginLog as ll
	inner join [User] as u
	on ll.UserID=u.IDUser
	order by ll.IDLoginLog desc
END
GO





-- insert example records

insert into Category values
	('Tablets','https://images-na.ssl-images-amazon.com/images/I/61zdQQORNML._AC._SR360,460.jpg'),
	('Monitors','https://images-na.ssl-images-amazon.com/images/I/51+LzUmDggL._AC._SR360,460.jpg'),
	('Laptops','https://images-na.ssl-images-amazon.com/images/I/41skC6wrvdL._AC._SR360,460.jpg'),
	('Keyboards','https://m.media-amazon.com/images/I/614PnXILlKL._AC_UL320_.jpg'),
	('Mice','https://m.media-amazon.com/images/I/61mpMH5TzkL._AC_UY218_.jpg'),
	('Mobile phones','https://m.media-amazon.com/images/I/81iA12vZHYL._AC_UY218_.jpg')
go

insert into Product values
	('Apple iPhone 11 (64 GB)','https://m.media-amazon.com/images/I/71i2XhHU3pL._AC_SX679_.jpg',100,'6.1" Liquid Retina HD (1792x828) True Tone zaslon 12MP+12/12MP kamera; A13 Bionic chip (2+4 jezgra) iOS 13',6),
	('SteelSeries Apex Pro','https://m.media-amazon.com/images/I/61jwRhtghXL._AC_SX679_.jpg',950,'Mechanical Gaming Keyboard - with Customisable Actuation - OLED Smart Display - Layout',4),
	('Samsung Galaxy Note20 Ultra 5G','https://m.media-amazon.com/images/I/71OFdblkIlL._AC_SL1500_.jpg',6000,'17.45 cm (6.9 inches)) 256 GB internal memory, 12 GB RAM, Hybrid SIM, Android, German Version, Mystic Black',6)
go

insert into [User] values 
	('admin','1234',1,'Mirko','Žaper','Poljièka cesta','21000','Split','Croatia')
go
