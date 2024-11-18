// import $ from 'jquery';
//
// $(document).ready(function() {
//
//   // Helper function to create FormData for request
//   function createFormData(examId = null) {
//     var formData = new FormData();
//     var file_excel = $('#file_Excel')[0].files[0];
//     var file_image = $('#file_Image')[0].files[0];
//     var name = $('#nameBaiThiThu').val();
//
//     // Get file lists
//     var countFileImage = document.getElementById('file_image_question').files.length;
//     var countFileMp3 = document.getElementById('file_listening').files.length;
//
//     // Append image question files
//     for (var x = 0; x < countFileImage; x++) {
//       formData.append("file_image_question", document.getElementById('file_image_question').files[x]);
//     }
//
//     // Append mp3 listening files
//     for (var i = 0; i < countFileMp3; i++) {
//       formData.append("file_listening", document.getElementById('file_listening').files[i]);
//     }
//
//     formData.append("file_excel", file_excel);
//     formData.append("file_image", file_image);
//     formData.append("name", name);
//
//     // If updating, add exam id
//     if (examId) {
//       formData.append("idExam", examId);
//     }
//
//     return formData;
//   }
//
//   // Open modal
//   $('#btnOpenModal').click(function() {
//     $('#examModal').show();
//   });
//
//   // Close modal
//   $('#btnCloseModal, #btnCloseModalFooter').click(function() {
//     $('#examModal').hide();
//   });
//
//   // Add new exam
//   $('#btnAddNewExam').click(function() {
//     var formData = createFormData();
//
//     $.ajax({
//       data: formData,
//       type: 'POST',
//       url: "http://localhost:8080/api/admin/practicetest/save",
//       enctype: 'multipart/form-data',
//       contentType: false,
//       cache: false,
//       processData: false,
//       success: function() {
//         $('#examModal').hide();
//         loadAllBaiThiThu();
//         $('#info-success').text("Thêm mới bài thi thử thành công");
//       },
//       error: function(e) {
//         console.error("ERROR: ", e);
//         alert("Có lỗi xảy ra.");
//         $('#examModal').hide();
//       }
//     });
//   });
//
//   // Update existing exam
//   $('#btnUpdateExam').click(function() {
//     var examId = $('#idExam').val();
//     var formData = createFormData(examId);
//
//     $.ajax({
//       data: formData,
//       type: 'POST',
//       url: "http://localhost:8080/api/admin/practicetest/save",
//       enctype: 'multipart/form-data',
//       contentType: false,
//       cache: false,
//       processData: false,
//       success: function() {
//         $('#examModal').hide();
//         $('#info-success').text("Cập nhật bài thi thử thành công");
//         loadAllBaiThiThu();
//       },
//       error: function(e) {
//         console.error("ERROR: ", e);
//         alert("Có lỗi xảy ra.");
//       }
//     });
//   });
//
//   // Edit exam
//   $(document).on('click', '.editBaiThiThu', function(e) {
//     e.preventDefault();
//     var examId = $(this).attr('id').split('.')[1];
//
//     $('#btnUpdateExam').show();
//     $('#btnAddNewExam').hide();
//     $('#examModal').show();
//
//     var modal = $('#examModal');
//     modal.find('.modal-header #titleModal').text("Cập nhật bài thi thử");
//     $('#examModal #idExam').val(examId);
//
//     $.ajax({
//       type: 'GET',
//       url: `http://localhost:8080/api/admin/practicetest/infoPracticeTest/${examId}`,
//       success: function(data) {
//         $('#examModal #nameBaiThiThu').val(data);
//       },
//       error: function(e) {
//         console.error("ERROR: ", e);
//         alert("Có lỗi xảy ra.");
//       }
//     });
//   });
//
//   // Delete exam
//   $(document).on('click', '.deleteBaiThiThu', function() {
//     var examId = $(this).attr('id').split('.')[1];
//
//     if (confirm("Bạn muốn xóa bài thi thử này?")) {
//       $.ajax({
//         type: 'POST',
//         url: `http://localhost:8080/api/admin/practicetest/delete/${examId}`,
//         success: function() {
//           loadAllBaiThiThu();
//           $('#info-success').text("Xóa bài thi thử thành công");
//         },
//         error: function(e) {
//           console.error("ERROR: ", e);
//           alert("Có lỗi xảy ra.");
//         }
//       });
//     }
//   });
//
//   // Load all exams
//   function loadAllBaiThiThu() {
//     $.ajax({
//       dataType: 'json',
//       type: 'GET',
//       url: "http://localhost:8080/api/admin/practicetest/loadpracticetest",
//       success: function(data) {
//         var jsonArr = data.map(item => {
//           var fields = item.split(',');
//           return {
//             baithithuid: fields[0].split(':')[1],
//             anhbaithithu: fields[1].split(':')[1],
//             tenbaithithu: fields[2].split(':')[1]
//           };
//         });
//
//         var tableHtml = jsonArr.map(item => {
//           return `
//                                 <tr>
//                                     <td>${item.baithithuid}</td>
//                                     <td>${item.tenbaithithu}</td>
//                                     <td>${item.anhbaithithu}</td>
//                                     <td>
//                                         <button id="edit.${item.baithithuid}" class="btn btn-primary editBaiThiThu">Chỉnh sửa</button>
//                                         <button id="delete.${item.baithithuid}" class="btn btn-danger deleteBaiThiThu">Xóa</button>
//                                     </td>
//                                 </tr>
//                             `;
//         }).join('');
//
//         $('#tableExam tbody').html(tableHtml);
//       },
//       error: function(e) {
//         console.error("ERROR: ", e);
//       }
//     });
//   }
//
//   loadAllBaiThiThu();
// });
