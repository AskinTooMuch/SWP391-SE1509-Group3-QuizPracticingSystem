<%-- 
    Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
    Created on : Oct 20, 2021, 12:40:57 PM
    Import question page
    Quiz practicing system

    Record of change:
    Date        Version     Author          Description
    20/10/21     1.0         ChucNVHE150618  First Deploy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script class="jsbin" src="https://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <title>Question Import</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${contextPath}/css/questionImport.css"/>
    </head>
    <body>
        <%-- Include header page --%>
        <jsp:include page="header.jsp"/>
        <div class="wrap" style="min-height: 55vh; padding-top: 5vh;">
            
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8">
                    <div class="row file-upload">
                        <div class="col-md-8">
                            Before you start uploading your own file, if this is
                            your first time then please download our template here 
                            first! Don't want any weird errors now do we?
                            <br>
                            <a class="btn btn-success" href ="${contextPath}/questionTemplate/questiontemplate.txt" download>Download</a>
                        </div>
                        <div class="image-upload-wrap col-md-4">
                            <input class="file-upload-input" type='file' onchange="openFile(event);" accept="text/plain" />
                            <div class="drag-text">
                              <h3>Drag and drop a file or select a file</h3>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
            </div>
            <span class="fileName">
                File name goes here
            </span>
            <div id='output'>
                ...
            </div>
        </div>
        <%-- Include footer page --%>
        <jsp:include page="footer.jsp"/>
    </body>
    <script>
        var openFile = function(event) {
        var input = event.target;

        $('.fileName').html(input.files[0].name);
        var reader = new FileReader();
        reader.onload = function(){
          var text = reader.result;
          var node = document.getElementById('output');
          node.innerText = text;
          console.log(reader.result.substring(0, 200));
        };
        reader.readAsText(input.files[0]);
      };
        
        function readURL(input) {
            if (input.files && input.files[0]) {

              var reader = new FileReader();

              reader.onload = function(e) {
                $('.image-upload-wrap').hide();

                $('.file-upload-image').attr('src', e.target.result);
                $('.file-upload-content').show();

                $('.image-title').html(input.files[0].name);
              };

              reader.readAsDataURL(input.files[0]);

            } else {
              removeUpload();
            }
        }

          function removeUpload() {
            $('.file-upload-input').replaceWith($('.file-upload-input').clone());
            $('.file-upload-content').hide();
            $('.image-upload-wrap').show();
          }
          $('.image-upload-wrap').bind('dragover', function () {
              $('.image-upload-wrap').addClass('image-dropping');
            });
            $('.image-upload-wrap').bind('dragleave', function () {
              $('.image-upload-wrap').removeClass('image-dropping');
          });
    </script>
</html>
