USE [COTS_QLKS]
GO
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A1', NULL, NULL, NULL, NULL, 0, 1, CAST(N'2021-12-09 00:27:51.203' AS DateTime), N'nguyensybao1403@gmail.com', N'', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', NULL, N'accountant@gmail.com', 1, N'Accountant', N'$2a$10$mWkz6jBwltMyexfVxi1gjeHNOxAGPSqOfXp.h3YAn1R0qRezE4/Za', N'', N'accountant', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A2', NULL, NULL, NULL, NULL, 0, 1, CAST(N'2021-12-09 00:28:03.313' AS DateTime), N'nguyensybao1403@gmail.com', N'', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', NULL, N'business@gmail.com', 1, N'Business', N'$2a$10$mWkz6jBwltMyexfVxi1gjeHNOxAGPSqOfXp.h3YAn1R0qRezE4/Za', N'', N'business', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A3', NULL, NULL, NULL, NULL, 0, 1, CAST(N'2021-12-09 00:28:18.763' AS DateTime), N'nguyensybao1403@gmail.com', N'', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', NULL, N'receptionists@gmail.com', 1, N'Receptionists', N'$2a$10$mWkz6jBwltMyexfVxi1gjeHNOxAGPSqOfXp.h3YAn1R0qRezE4/Za', N'', N'receptionists', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A4', NULL, NULL, NULL, NULL, 0, 1, CAST(N'2021-12-09 00:50:28.697' AS DateTime), N'nguyensybao1403@gmail.com', N'', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', NULL, N'member@gmail.com', 1, N'Member', N'$2a$10$mWkz6jBwltMyexfVxi1gjeHNOxAGPSqOfXp.h3YAn1R0qRezE4/Za', N'', N'member', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'ADMIN', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Đà Nẵng', N'ADMIN', N'https://hoc24.vn/images/avt/avt6384329_256by256.jpg', CAST(N'2000-03-14' AS Date), N'nguyensybao1403@gmail.com', 1, N'Nguyễn Sỹ Bảo', N'$2a$10$mWkz6jBwltMyexfVxi1gjeHNOxAGPSqOfXp.h3YAn1R0qRezE4/Za', N'0796788770', N'admin', NULL)
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R1', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'DIRECTOR', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Challenger-Summoner-Icon.jpg', N'Giám đốc')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R2', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'ACCOUNTANT', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Grand-Master-Summoner-Icon.jpg', N'Kế toán')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R3', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'BUSINESS', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Master-Summoner-Icon.jpg', N'Kinh doanh')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R4', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'RECEPTIONISTS', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Diamond-Summoner-Icon.jpg', N'Lễ tân')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R5', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'MEMBER', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Platinum-Summoner-Icon.jpg', N'Thành viên (khách)')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'ADMIN', N'R1')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'ADMIN', N'R2')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'ADMIN', N'R3')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'ADMIN', N'R4')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'ADMIN', N'R5')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'A1', N'R2')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'A2', N'R3')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'A3', N'R4')
INSERT [dbo].[account_role] ([id], [id_role]) VALUES (N'A4', N'R5')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name], [price]) VALUES (N'RT1', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Cửa sổ view biển, truyền hình cáp quang, wiffi Free băng thông rộng, tivi 40 in, bộ Salon, trà cà phê, hoa quả, điều hòa mát, nóng lạnh,vách tắm đứng, máy sấy tóc, dao', N'https://seahotel.webhotel.vn/files/images/Room/suite-balcony/601---Suite-balcony-(2).jpg', N'PHÒNG SUITE BALCONY', 200)
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name], [price]) VALUES (N'RT2', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Cửa sổ view biển, truyền hình cáp quang, wiffi Free băng thông rộng, tivi 40 in, điều hòa mát, nóng lạnh, máy sấy tóc, dao cạo, nước uống miễn phí, bàn ghế uống nước,', N'https://seahotel.webhotel.vn/files/images/Room/deluxe-double/602---Deluxe-double-(1).jpg', N'PHÒNG DELUXE DOUBLE', 360)
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name], [price]) VALUES (N'RT3', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Truyền hình cáp quang, wiffi Free băng thông rộng, tivi 40 in, điều hòa mát, nóng lạnh, máy sấy tóc, dao cạo, nước uống miễn phí, bàn ghế uống nước, giường ngủ: 2 giường 1m6,', N'https://seahotel.webhotel.vn/files/images/Room/deluxe-twin-room/PX_03500.jpg', N'PHÒNG DELUXE TWIN', 250)
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name], [price]) VALUES (N'RT4', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Cửa sổ view biển, truyền hình cáp quang, wiffi Free băng thông rộng, tivi 40 in, bộ Salon, trà cà phê, hoa quả, điều hòa mát, nóng lạnh,vách tắm đứng, máy sấy tóc, dao', N'https://seahotel.webhotel.vn/files/images/Room/Family-Room/801---Family-(1).jpg', N'PHÒNG GIA ĐÌNH', 300)
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name], [price]) VALUES (N'RT5', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Phòng Deluxe có thiết kế hài hòa, nội thất sang trọng với đầy đủ tiện nghi hiện đại. Diện tích khoảng 60-65m2 / phòng được trang bị cửa sổ và ban công.', N'https://fiboholiday.webhotel.vn/files/images/Room/deluxe/406_5.jpg', N'PHÒNG ĐƠN', 120)
INSERT [dbo].[service] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [image], [name], [price], [quantity]) VALUES (N'S1', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Giường phụ có kích thước: 2m x 0,9m, có ga trải giường, ...', N'https://booking-guarantee.com/files/files_1/Service/extra-bed.jpg', N'JUNIOR SUITE BALCONY', 6.5, 0)
INSERT [dbo].[service] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [image], [name], [price], [quantity]) VALUES (N'S2', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Để tránh bất kỳ vụ lừa đảo nào có thể xảy ra trên đường từ sân bay đến khách sạn của chúng tôi, chúng tôi rất vui lòng sắp xếp cho bạn xe ô tô riêng sạch sẽ, hiện đại và tiện nghi để đón tại sân bay; phù hợp với chi tiết chuyến bay của bạn .', N'https://booking-guarantee.com/files/files_1/Service/4940luxury-taxi.jpg', N'Đón tại sân bay - xe 4 chỗ', 10.6, 0)
INSERT [dbo].[service] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [image], [name], [price], [quantity]) VALUES (N'S3', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Đối với nhóm 3 hoặc 4 người có hành lý thì xe 7 chỗ sẽ thoải mái hơn.', N'https://booking-guarantee.com/files/files_1/Service/for-7.png', N'Đón sân bay - xe 7 chỗ', 14.55, 0)
INSERT [dbo].[service] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [image], [name], [price], [quantity]) VALUES (N'S4', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Xe van 16 chỗ là phương tiện phù hợp cho nhóm từ 5 đến 8 người với hành lý', N'https://booking-guarantee.com/files/files_1/Service/for-16.png', N'Đón tại sân bay - xe van 16 chỗ', 17.89, 0)
INSERT [dbo].[service] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [image], [name], [price], [quantity]) VALUES (N'S5', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, N'Chúng tôi rất vui mừng được sắp xếp visa cho bạn khi đến Sân bay Nội Bài, Hà Nội / Sân bay Quốc tế Tôn Sơn Nhất, Sài Gòn. Chúng tôi nhận thấy rằng nhiều khách của chúng tôi thích chúng tôi sắp xếp visa Việt Nam của họ vì nó giúp họ tiết kiệm thời gian và trong một số trường hợp, chi phí sẽ rẻ hơn. Vui lòng liên hệ trước với chúng tôi để biết chi phí xin thị thực, lệ phí và biết thêm thông tin về quy trình.', N'https://booking-guarantee.com/files/files_1/Service/-1x-1-(1).jpg', N'Dịch vụ làm visa', 19.78, 0)
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R01', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 40, 4, NULL, 1, N'A101', 1.2, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R02', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 45, 5, NULL, 1, N'A102', 1.2, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R03', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 6, NULL, 1, N'A103', 3.2, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R04', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 55, 7, NULL, 1, N'A104', 5.5, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R05', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 8, NULL, 1, N'A105', 7.8, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R06', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 65, 9, NULL, 1, N'A106', 9, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R07', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 70, 10, NULL, 1, N'A107', 15.9, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R08', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 14, NULL, 1, N'A108', 27.6, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R09', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 16, NULL, 1, N'A109', 38.4, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R10', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 8, NULL, 1, N'A110', 10.5, N'EMPTY', N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R11', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 40, 6, NULL, 2, N'A201', 4.8, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R12', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 45, 7, NULL, 2, N'A202', 5.6, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R13', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 8, NULL, 2, N'A203', 6.1, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R14', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 55, 9, NULL, 2, N'A204', 6.9, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R15', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 10, NULL, 2, N'A205', 9.9, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R16', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 65, 12, NULL, 2, N'A206', 11.4, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R17', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 70, 14, NULL, 2, N'A207', 15.9, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R18', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 16, NULL, 2, N'A208', 27.6, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R19', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 18, NULL, 2, N'A209', 38.4, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R20', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 8, NULL, 2, N'A210', 10.5, N'EMPTY', N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R21', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 40, 4, NULL, 3, N'A301', 1.2, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R22', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 45, 5, NULL, 3, N'A302', 1.2, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R23', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 6, NULL, 3, N'A303', 3.2, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R24', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 55, 7, NULL, 3, N'A304', 5.5, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R25', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 8, NULL, 3, N'A305', 7.8, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R26', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 65, 9, NULL, 3, N'A306', 9, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R27', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 70, 10, NULL, 3, N'A307', 15.9, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R28', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 14, NULL, 3, N'A308', 27.6, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R29', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 16, NULL, 3, N'A309', 38.4, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R30', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 8, NULL, 3, N'A310', 10.5, N'EMPTY', N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R31', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 40, 6, NULL, 4, N'A401', 4.8, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R32', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 45, 7, NULL, 4, N'A402', 5.6, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R33', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 8, NULL, 4, N'A403', 6.1, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R34', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 55, 9, NULL, 4, N'A404', 6.9, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R35', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 10, NULL, 4, N'A405', 9.9, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R36', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 65, 12, NULL, 4, N'A406', 11.4, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R37', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 70, 14, NULL, 4, N'A407', 15.9, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R38', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 16, NULL, 4, N'A408', 27.6, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R39', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 18, NULL, 4, N'A409', 38.4, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R40', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 8, NULL, 4, N'A410', 10.5, N'EMPTY', N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R41', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 40, 4, NULL, 3, N'A501', 1.2, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R42', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 45, 5, NULL, 3, N'A502', 1.2, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R43', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 6, NULL, 3, N'A503', 3.2, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R44', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 55, 7, NULL, 3, N'A504', 5.5, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R45', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 60, 8, NULL, 3, N'A505', 7.8, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R46', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 65, 9, NULL, 3, N'A506', 9, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R47', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 70, 10, NULL, 3, N'A507', 15.9, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R48', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 14, NULL, 3, N'A508', 27.6, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R49', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 80, 16, NULL, 3, N'A509', 38.4, N'EMPTY', N'RT5')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [area], [customers_num], [description], [floor], [name], [price_incurred], [room_state], [id_room_type]) VALUES (N'R50', NULL, NULL, NULL, NULL, 0, 1, NULL, NULL, 50, 8, NULL, 3, N'A510', 10.5, N'EMPTY', N'RT5')
