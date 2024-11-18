// import $ from 'jquery';
// import EASYMDE from 'easymde';
//
// $(document).ready(function() {
//   var easymde;
//
//   //default. load all object baiGrammar
//   loadAllGrammar();
//   // creat markdown
//   easymde = new EASYMDE({
//     element: document.getElementById("markdown-editor"),
//     spellChecker: false,
//   });
//
//   function loadAllGrammar(){
//     $.ajax({
//       dataType : 'json',
//       type:'GET',
//       url:"http://localhost:8080/api/admin/grammar/loadGrammar",
//       success: function(data){
//         // convert array to json type
//         var jsonArray = new Array();
//         var fields, id, anhbaigrammar, tenbaigrammar;
//         for(var i = 0; i < data.length; i++) {
//           var jsonObject = new Object();
//           fields = data[i].split(',');
//
//           id = fields[0].split(':');
//           jsonObject.baigrammarid = id[1];
//
//           tenbaigrammar = fields[1].split(':');
//           jsonObject.tenbaigrammar = tenbaigrammar[1];
//
//           anhbaigrammar = fields[2].split(':');
//           jsonObject.anhbaigrammar = anhbaigrammar[1];
//
//           jsonArray.push(jsonObject);
//         }
//
//         var jsonArr = JSON.parse(JSON.stringify(jsonArray));
//         var trHTML = "";
//         for(var x = 0; x < jsonArr.length; x++) {
//           trHTML += '<tr><td class= "center">' + jsonArr[x].baigrammarid + '</td>'
//               + '<td class= "center">' + jsonArr[x].tenbaigrammar + '</td>'
//               + '<td class= "center">' + jsonArr[x].anhbaigrammar + '</td>'
//               + '<td class = "center"> <a id="edit.' + jsonArr[x].baigrammarid + '"'
//               + 'class="yellow editBaiGrammar"><button class="btn btn-warning">Cập nhật</button></a> '
//               + ' <a id="delete.' + jsonArr[x].baigrammarid + '"'
//               + 'class="red deleteBaiGrammar" ><button class="btn btn-danger">Xóa</button></a> </td>'
//               + '</tr>';
//         }
//         $('tbody').html(trHTML);
//       }, error : function(e) {
//         alert("error");
//         console.log("ERROR: ", e);
//       }
//     });
//   }
//
//   $('#btnOpenModal').click(function() {
//     $('#grammarModal').show();
//   });
//
//   // Close modal
//   $('#btnCloseModal, #btnCloseModalFooter').click(function() {
//     $('#grammarModal').hide();
//   });
//
//   $('#btnAddNewGrammar').click(function() {
//     var formData = new FormData();
//     var file_image = $('#file_Image_Grammar')[0].files[0];
//     var name = $('#nameGrammar').val();
//     var contentMarkdown =  easymde.value();
//     var contentHTML = easymde.options.previewRender(contentMarkdown);
//
//     formData.append("grammarname", name);
//     formData.append("grammarimage", file_image);
//     formData.append("grammarcontenthtml", contentHTML);
//     formData.append("grammarcontentmarkdown", contentMarkdown);
//
//     console.log(name)
//     console.log(file_image)
//     console.log(contentMarkdown)
//     console.log(contentHTML)
//
//     $.ajax({
//       data: formData,
//       type:'POST',
//       url:"http://localhost:8080/api/admin/grammar/save",
//       enctype : 'multipart/form-data',
//       contentType : false,
//       cache : false,
//       processData : false,
//       success: function(){
//         $('#grammarModal').hide();
//         loadAllGrammar();
//         $('#info-success').text("Thêm mới bài grammar thành công");
//       },
//       error : function(e) {
//         alert("error");
//         $('#grammarModal').hide();
//         console.log("ERROR: ", e);
//       }
//     });
//   });
//
//   $(document).on('click','.deleteBaiGrammar',function(){
//     var deleteId = $(this).attr('id');
//     var fields = deleteId.split('.');
//     var idBaiGrammar = fields[1];
//
//     if(confirm("Bạn muốn xóa bài grammar này?")) {
//       $.ajax({
//         type:'POST',
//         url:"http://localhost:8080/api/admin/grammar/delete/"+idBaiGrammar,
//         success: function(){
//           loadAllGrammar();
//           $('#info-success').text("Xóa bài grammar thành công");
//         },
//         error : function(e) {
//           alert("error");
//           console.log("ERROR: ", e);
//         }
//       });
//     }
//   });
//
//   $(document).on('click','.editBaiGrammar',function(){
//     $('#grammarModal').show();
//     var editId = $(this).attr('id');
//     var fields = editId.split('.');
//     var idBaiGrammar = fields[1];
//     var anhbaigrammar, contentgrammar;
//
//     $.ajax({
//       type:'GET',
//       url:"http://localhost:8080/api/admin/grammar/infoGrammar/"+idBaiGrammar,
//       success: function(data){
//         var jsonObject = new Object();
//         fields = data.split('|');
//
//         var id = fields[0].split('==');
//         jsonObject.tenbaigrammar = id[1];
//
//         anhbaigrammar = fields[1].split('==');
//         jsonObject.anhbaigrammar = anhbaigrammar[1];
//
//         contentgrammar = fields[2].split('==');
//         jsonObject.contentgrammar = contentgrammar[1];
//
//         var modal = $('#grammarModal');
//         $('#grammarModal #idGrammarModal').val(idBaiGrammar);
//         modal.find('.modal-body #nameGrammar').val(jsonObject.tenbaigrammar);
//         modal.find('.modal-header #titleModal').text("Cập nhật bài ngữ pháp");
//
//         easymde.value(jsonObject.contentgrammar);
//
//         $('#btnUpdate').show();
//         $('#btnAddNewGrammar').hide();
//         $('#grammarModal').modal('show');
//       },
//       error : function(e) {
//         alert("error");
//         console.log("ERROR: ", e);
//       }
//     });
//
//     $('#btnUpdate').click(function() {
//       var formData = new FormData();
//       var name = $('#nameGrammar').val();
//       var contentMarkdown =  easymde.value(); //get from textarea markdown
//       var contentHTML = easymde.options.previewRender(contentMarkdown);
//       var file_image;
//
//       if($('#file_Image_Grammar').get(0).files.length != 0) {
//         file_image = $('#file_Image_Grammar')[0].files[0];
//         formData.append("file_image",file_image);
//       } else {
//         formData.append("file_image", " ");
//       }
//
//       formData.append("idGrammar", idBaiGrammar);
//       formData.append("name", name);
//       formData.append("contentMarkdown", contentMarkdown);
//       formData.append("contentHtml", contentHTML);
//
//       $.ajax({
//         data: formData,
//         type:'POST',
//         url:"http://localhost:8080/api/admin/grammar/update",
//         enctype : 'multipart/form-data',
//         contentType : false,
//         cache : false,
//         processData : false,
//         success: function(){
//           $('#grammarModal').modal('hide');
//           $('#info-success').text("Cập nhật bài grammar thành công");
//           loadAllGrammar();
//         },
//         error : function(e) {
//           alert("error");
//           console.log("ERROR: ", e);
//         }
//       });
//     });
//   });
// });
//
//
