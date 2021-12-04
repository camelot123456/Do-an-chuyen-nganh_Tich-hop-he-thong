USE [COTS_QLKS]
GO
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A1', CAST(N'2021-11-30 13:13:48.750' AS DateTime), N'GUEST', NULL, NULL, NULL, 1, CAST(N'2021-12-04 23:55:02.077' AS DateTime), N'GUEST', N'Đà Nẵng', N'GOOGLE', N'https://lh3.googleusercontent.com/a-/AOh14Gh2TqPHDrxYNroPxhto8JjeFhMk7eut-_j0vCpqeA=s96-c', CAST(N'2000-03-14 00:00:00.000' AS DateTime), N'nguyensybao1403@gmail.com', 1, N'Sỹ Bảo Nguyễn', N'', N'0796788770', N'', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A2', CAST(N'2021-11-30 10:51:06.860' AS DateTime), N'GUEST', NULL, NULL, NULL, 1, CAST(N'2021-12-04 23:36:54.970' AS DateTime), N'GUEST', N'Hà Nội', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', CAST(N'2000-12-25 00:00:00.000' AS DateTime), N'votuanviet27101990@gmail.com', 1, N'Võ Tuấn Việt', N'$2a$10$BV9AoHImTROSgWRL.Ji60eyUvoEDzBsYm2xfvTRpJRhf7/dvJU9AC', N'0796788770', N'user', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A3', CAST(N'2021-11-30 12:18:30.633' AS DateTime), N'GUEST', NULL, NULL, NULL, 1, CAST(N'2021-12-04 23:37:09.080' AS DateTime), N'GUEST', N'Huế', N'LOCAL', N'https://png.pngtree.com/png-vector/20190710/ourmid/pngtree-user-vector-avatar-png-image_1541962.jpg', CAST(N'2000-01-22 00:00:00.000' AS DateTime), N'thongvo0312@gmail.com', 1, N'Võ Như Thống', N'$2a$10$NqTiC0leUNp2ROmAOhBHeubb19w196Dzg0pcyPt6Ka5wbk93iimsa', N'0796788770', N'thongpro_vn123456', NULL)
INSERT [dbo].[account] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [address], [auth_provider], [avatar], [birthday], [email], [gender], [name], [password], [phone_num], [username], [verification_code]) VALUES (N'A4', CAST(N'2021-11-30 13:14:48.883' AS DateTime), N'GUEST', NULL, NULL, NULL, 1, CAST(N'2021-12-04 23:53:59.343' AS DateTime), N'GUEST', N'Đà Nẵng', N'FACEBOOK', N'https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=1898417417025685&height=50&width=50&ext=1641228839&hash=AeQUUjg7HrAQEl9OLjQ', CAST(N'2000-03-14 00:00:00.000' AS DateTime), N'nguyensybao1403@gmail.com', 1, N'Bảo Nguyễn', N'', N'0796788770', N'', NULL)
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R1', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'DIRECTOR', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Challenger-Summoner-Icon.jpg', N'Giám đốc')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R2', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'ACCOUNTANT', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Grand-Master-Summoner-Icon.jpg', N'Kế toán')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R3', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'BUSINESS', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Master-Summoner-Icon.jpg', N'Kinh doanh')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R4', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'RECEPTIONISTS', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Diamond-Summoner-Icon.jpg', N'Lễ tân')
INSERT [dbo].[role] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [code], [description], [logo], [name]) VALUES (N'R5', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'MEMBER', NULL, N'https://img.rankedboost.com/wp-content/uploads/2020/10/Season-10-Platinum-Summoner-Icon.jpg', N'Thành viên (khách)')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name]) VALUES (N'RT1', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'Phòng Deluxe giường đôi có máy lạnh và ấm điện. Giá phòng Deluxe giường đôi bao gồm 2 người lớn và 2 em bé dưới 6 tuổi miễn phí ngủ chung giường với bố mẹ. Một phòng tối đa 3 người lớn hoặc hai người lớn và hai em bé. Phát sinh người lớn thứ 3 thì phụ thu 400,000vnd/đêm/phòng (bao gồm extrabed và ăn sáng). Trẻ em từ 6 đến dưới 12 tuổi phụ thu 125,000vnd/bé/đêm (bao gồm ăn sáng), tối đa 2 bé. Trẻ em từ 12 tuổi trở lên tính như người lớn v..v.', N'https://new-hls.s3.amazonaws.com/hbe/data/a9f4f9fa-d6a1-1574585805-4ed0-aeda-3b346800f4bc/gallery/room/thumbs/sm_thumb_225822094_1574587383.jpg', N'MEGA SALES 2021')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name]) VALUES (N'RT2', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'Phòng Deluxe 2 giường đơn có ấm điện và máy lạnh. Giá phòng Deluxe 2 giường đơn bao gồm 2 người lớn và 2 em bé dưới 6 tuổi miễn phí ngủ chung giường với bố mẹ. Một phòng tối đa 3 người lớn hoặc hai người lớn và hai em bé. Phát sinh người lớn thứ 3 thì phụ thu 400,000vnd/đêm/phòng (bao gồm extrabed và ăn sáng). Trẻ em từ 6 đến dưới 12 tuổi phụ thu 125,000vnd/bé/đêm (bao gồm ăn sáng), tối đa 2 bé. Trẻ em từ 12 tuổi trở lên tính như người lớn v..v.', N'https://new-hls.s3.amazonaws.com/hbe/data/a9f4f9fa-d6a1-1574585805-4ed0-aeda-3b346800f4bc/gallery/room/thumbs/sm_thumb_225822261_1574587751.jpg', N'Deluxe Triple')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name]) VALUES (N'RT3', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'Phòng Deluxe Gia Đình thích hợp cho gia đình với 1 giường đôi rộng và 1 giường đơn.', N'https://new-hls.s3.amazonaws.com/hbe/data/a9f4f9fa-d6a1-1574585805-4ed0-aeda-3b346800f4bc/gallery/room/thumbs/sm_thumb_225822284_1574589426.jpg', N'Deluxe Family')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name]) VALUES (N'RT4', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'Phòng Suite có máy lạnh, mini bar và tivi vệ tinh.', N'https://new-hls.s3.amazonaws.com/hbe/data/a9f4f9fa-d6a1-1574585805-4ed0-aeda-3b346800f4bc/gallery/room/thumbs/sm_thumb_225822240_1574588393.jpg', N'Executive Suite')
INSERT [dbo].[room_type] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [description], [logo], [name]) VALUES (N'RT5', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, N'Phòng Tổng Thống sang trọng có đủ mọi tiện nghi cho giải trí và thư giãn.', N'https://new-hls.s3.amazonaws.com/hbe/data/a9f4f9fa-d6a1-1574585805-4ed0-aeda-3b346800f4bc/gallery/room/thumbs/sm_thumb_tst_6395_1576737262.jpg', N'Presidential Suite')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P1', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/1617186135419jG/2-queen-resort-classic-ocean-view', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'Resort Classic Room (King/Twin)', 9700000, 0, N'EMPTY', NULL, N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P2', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/1617186194713AH/1-king-son-tra-ocean-view---king-bedroom.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 2, N'Sơn Trà Room', 8029400, 0, N'EMPTY', NULL, N'RT1')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P3', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/1617186169227TH/1-king-classic-terrace-suite-ocean-view---king-bed.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 2, N'Sơn Trà Terrace Suite', 9135600, 0, N'EMPTY', NULL, N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P4', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686aH/1-king-club-terrace-suite-ocean-view---bedroom.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'Club Terrace Suite', 1102300, 0, N'EMPTY', NULL, N'RT3')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P5', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686bz/1-bedroom-club-peninsula-suite-ocean-view---bedroom-with-lady.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'Club Peninsula Suite', 7901200, 0, N'EMPTY', NULL, N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P6', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686JA/spa-lagoon-villa---treatment-room.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'Spa Lagoon Villa', 1356400, 0, N'EMPTY', NULL, N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P7', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686mo/seaside-villa-on-the-rocks---kingbed-portrait.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 3, N'1BR Seaside Villa On The Rocks', 1565100, 0, N'EMPTY', NULL, N'RT4')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P8', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686Vq/1-bedroom-seaside-villa-by-the-beach---exterior.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'1BR Seaside Villa By The Beach', 2156400, 0, N'EMPTY', NULL, N'RT2')
INSERT [dbo].[room] ([id], [create_at], [create_by], [delete_at], [delete_by], [deleted], [enabled], [modified_at], [modified_by], [adults], [avatar], [children], [description], [end_date], [floor], [name], [price], [quantity], [room_state], [start_time], [id_room_type]) VALUES (N'P9', NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, N'https://tripi.vn/cdn-cgi/image/width=640,height=640/https://storage.googleapis.com/hms_prod/photo/img/2686Zl/heavenly-penthouse---private-pool-view.jpg', 0, N'Khách lớn hơn 12 tuổi sẽ được xem như người lớn

Trẻ em dưới 6 tuổi có thể lưu trú miễn phí

Trẻ em ở các tuổi còn lại lưu trú có thể mất phí', NULL, 1, N'Heavenly Penthouse', 2322000, 0, N'EMPTY', NULL, N'RT3')
