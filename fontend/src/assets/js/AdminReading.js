// import $ from 'jquery';
//
// $(document).ready(function() {
//   let changeImage = false;
//   let changeExcel = false;
//
//   // Lấy dữ liệu ban đầu
//   ajaxGet(1);
//
//   function ajaxGet(page) {
//     const doKho = $("#doKhoSearch").val();
//     const part = $("#partSearch").val();
//
//     $.ajax({
//       type: "GET",
//       url: `http://localhost:8080/api/admin/reading/all?page=${page}&doKho=${doKho}&part=${part}`,
//       success: function(result) {
//         // Xử lý dữ liệu nhận về
//         $.each(result.content, function(i, baiDoc) {
//           let baiDocRow = `<tr style="text-align: center;">
//             <td width="5%">${baiDoc.id}</td>
//             <td>${baiDoc.tenBaiDoc}</td>
//             <td>${getPartName(baiDoc.part)}</td>
//             <td>${getDoKhoName(baiDoc.doKho)}</td>
//             <td>
//               <input type="hidden" value="${baiDoc.id}">
//               <button class="btn btn-primary btnCapNhatBaiDoc">Cập nhật</button>
//               <button class="btn btn-danger btnXoaBaiDoc">Xóa</button>
//             </td>
//           </tr>`;
//           $('.baiDocTable tbody').append(baiDocRow);
//         });
//
//         // Xử lý phân trang
//         if (result.totalPages > 1) {
//           for (let numberPage = 1; numberPage <= result.totalPages; numberPage++) {
//             const li = `<li class="page-item"><a class="pageNumber">${numberPage}</a></li>`;
//             $('.pagination').append(li);
//           }
//
//           // Active trang hiện tại
//           $(".pageNumber").each(function(index) {
//             if ($(this).text() == page) {
//               $(this).parent().removeClass().addClass("page-item active");
//             }
//           });
//         }
//       },
//       error: function(e) {
//         alert("Error: " + e);
//         console.log("Error", e);
//       }
//     });
//   }
//
//   function getPartName(part) {
//     if (part === 5) return 'PART5_COMPLETE_SENTENCE';
//     if (part === 6) return 'PART6_COMPLETE_THE_PARAGRAPH';
//     return 'PART7_READING_COMPREHENSION';
//   }
//
//   function getDoKhoName(doKho) {
//     if (doKho === 1) return 'Dễ';
//     if (doKho === 2) return 'Trung bình';
//     return 'Khó';
//   }
//
//   // Sự kiện khi click vào duyệt bài đọc
//   $(document).on('click', '#btnDuyetBaiDoc', function(event) {
//     event.preventDefault();
//     $('.baiDocTable tbody tr').remove();
//     $('.pagination li').remove();
//     ajaxGet(1);
//   });
//
//   // Sự kiện khi click vào thêm mới bài đọc
//   $('.btnThemBaiDoc').on("click", function(event) {
//     event.preventDefault();
//     $('#baiDocModal').modal();
//     $('.baiDocForm #id').prop("disabled", true);
//     $('#formBaiDoc').removeClass().addClass("addForm");
//     $('#formBaiDoc #btnSubmit').removeClass().addClass("btn btn-primary btnSaveForm");
//   });
//
//   // Sự kiện khi click vào cập nhật bài đọc
//   $(document).on('click', '.btnCapNhatBaiDoc', function(event) {
//     event.preventDefault();
//     const baiDocId = $(this).parent().find('input').val();
//     $('#formBaiDoc').removeClass().addClass("updateForm");
//     $('#formBaiDoc #btnSubmit').removeClass().addClass("btn btn-primary btnUpdateForm");
//
//     $.get(`http://localhost:8080/api/admin/reading/${baiDocId}`, function(baiDoc) {
//       $('#id').val(baiDoc.id);
//       $('#tenBaiDoc').val(baiDoc.tenBaiDoc);
//       $('#doKho').val(baiDoc.doKho);
//       $('#phanThi').val(baiDoc.part);
//       $('#script').val(baiDoc.script);
//       $("img").attr("src", `http://localhost:8080/file/images/baiDocId=${baiDoc.id}.png`);
//       $("#previewImage").removeClass("hidden");
//       $("#linkExcel").attr("href", `http://localhost:8080/file/excel/baiDocId=${baiDoc.id}.xlsx`);
//       $("#linkExcel").removeClass("hidden");
//     });
//     $('#baiDocModal').modal();
//   });
//
//   // Sự kiện khi ẩn modal
//   $('#baiDocModal').on('hidden.bs.modal', function() {
//     $('#formBaiDoc').removeClass().addClass("baiDocForm");
//     $('#formBaiDoc #btnSubmit').removeClass().addClass("btn btn-primary");
//     resetForm();
//   });
//
//   // Xóa bài đọc
//   $(document).on("click", ".btnXoaBaiDoc", function() {
//     const baiDocId = $(this).parent().find('input').val();
//     const confirmation = confirm("Bạn chắc chắn xóa bài đọc này ?");
//     if (confirmation) {
//       $.ajax({
//         type: "DELETE",
//         url: `http://localhost:8080/api/admin/reading/delete/${baiDocId}`,
//         success: function() {
//           resetDataForDelete();
//           alert("Xóa thành công");
//         },
//         error: function(e) {
//           alert("Không thể xóa bài đọc này! Hãy kiểm tra lại");
//           console.log("ERROR: ", e);
//         }
//       });
//     }
//   });
//
//   // Reset lại dữ liệu sau khi thêm, sửa, xóa
//   function resetData() {
//     $('.baiDocTable tbody tr').remove();
//     const page = $('li.active').children().text();
//     $('.pagination li').remove();
//     ajaxGet(page);
//   }
//
//   // Reset dữ liệu sau khi xóa
//   function resetDataForDelete() {
//     const count = $('.baiDocTable tbody').children().length;
//     $('.baiDocTable tbody tr').remove();
//     const page = $('li.active').children().text();
//     $('.pagination li').remove();
//     if (count == 1) {
//       ajaxGet(page - 1);
//     } else {
//       ajaxGet(page);
//     }
//   }
//
//   // Phân trang
//   $(document).on('click', '.pageNumber', function(event) {
//     const page = $(this).text();
//     $('.baiDocTable tbody tr').remove();
//     $('.pagination li').remove();
//     ajaxGet(page);
//   });
//
//   // Validate form
//   $("#formBaiDoc").validate({
//     errorElement: "p",
//     errorClass: "error-message",
//     rules: {
//       tenBaiDoc: { required: true, maxlength: 100 },
//       photoBaiDoc: { required: true },
//       fileCauHoi: { required: true },
//       script: {
//         required: {
//           depends: function() {
//             return $("#phanThi").val() === '5' || $("#phanThi").val() === '6' || $("#phanThi").val() === '7';
//           }
//         }
//       }
//     },
//     messages: {
//       tenBaiDoc: { required: "Bạn không được để trống phần này", maxlength: "Tiêu đề dài nhất là 100 chữ cái" },
//       photoBaiDoc: { required: "Bạn không được để trống phần này" },
//       fileExcelCauHoi: { required: "Bạn không được để trống phần này" },
//       script: { required: "Bạn không được để trống phần này" }
//     },
//     submitHandler: function(form) {
//       const formData = new FormData(form);
//       saveFunction(formData);
//     }
//   });
//
//   // Lưu bài đọc mới
//   $(document).on('click', '.btnSaveForm', function(event) {
//     event.preventDefault();
//     if ($("#formBaiDoc").valid()) {
//       $("#formBaiDoc").submit();
//     }
//   });
//
//   // Kiểm tra file ảnh
//   $("#photoBaiDoc").change(function(event) {
//     const fileExtension = ['jpg', 'png'];
//     const fileName = $(this).val().split('.').pop().toLowerCase();
//     if ($.inArray(fileName, fileExtension) === -1) {
//       alert("Chỉ cho phép ảnh định dạng JPEG, PNG");
//       $(this).wrap('<form>').closest('form').get(0).reset();
//       $(this).unwrap();
//       $("#previewImage").addClass("hidden");
//     } else {
//       const files = event.target.files;
//       $("#previewImage").attr("src", URL.createObjectURL(files[0]));
//       $("#previewImage").removeClass("hidden");
//     }
//     changeImage = true;
//   });
//
//   // Kiểm tra file Excel
//   $("#fileCauHoi").change(function() {
//     const fileExtension = ['xlsx'];
//     const fileName = $(this).val().split('.').pop().toLowerCase();
//     if ($.inArray(fileName, fileExtension) === -1) {
//       alert("Chỉ cho phép file Excel định dạng xlsx");
//       $(this).wrap('<form>').closest('form').get(0).reset();
//       $(this).unwrap();
//     }
//   });
//
//   // Gửi request lưu bài đọc
//   function saveFunction(formData) {
//     $.ajax({
//       type: "POST",
//       url: "http://localhost:8080/webtoeic/api/admin/bai-doc/save",
//       enctype: 'multipart/form-data',
//       data: formData,
//       processData: false,
//       contentType: false,
//       cache: false,
//       timeout: 1000000,
//       success: function(response) {
//         alert("Thêm thành công");
//         resetData();
//       },
//       error: function(e) {
//         alert("Lỗi khi thêm bài đọc");
//         console.log("ERROR: ", e);
//       }
//     });
//   }
//
//   // Xử lý kết quả khi cập nhật bài đọc
//   function updateFunction(formData, id) {
//     $.ajax({
//       type: "POST",
//       url: `http://localhost:8080/webtoeic/api/admin/bai-doc/update/${id}`,
//       enctype: 'multipart/form-data',
//       data: formData,
//       processData: false,
//       contentType: false,
//       cache: false,
//       timeout: 1000000,
//       success: function(response) {
//         alert("Cập nhật thành công");
//         resetData();
//       },
//       error: function(e) {
//         alert("Lỗi khi cập nhật bài đọc");
//         console.log("ERROR: ", e);
//       }
//     });
//   }
//
//   // Reset form
//   function resetForm() {
//     $("#formBaiDoc")[0].reset();
//     $("#previewImage").addClass("hidden");
//     $("#linkExcel").addClass("hidden");
//     changeImage = false;
//     changeExcel = false;
//   }
// });
