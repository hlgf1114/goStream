var main = {

    init : function() {

        var _this = this;

        $('#btn-save').on('click', function() {
            _this.save();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        })

    },

    save : function () {


        var bar = $('.bar');
        var percent = $('.percent');
        var status = $('#status');

        var postData = new FormData();
        postData.append('title', $('#title').val());
        postData.append('content', $('#content').val());
        postData.append('uploader', $('#id').val());
        postData.append('file', $('#uploadVideo')[0].files[0]);
        /*
        var file = $('#uploadVideo');
        var data = {
            uploader : $('#id').val(),
            title : $('#title').val(),
            content : $('#content').val(),
            file : file
        }
        */

        $.ajax({
            xhr : function() {

                var xhr = new window.XMLHttpRequest();

                xhr.upload.addEventListener("progress", function(evt) {
                    if(evt.lengthComputable) {
                        var percentComplete = Math.floor((evt.loaded / evt.total) * 100);

                        // 뭔가 하기
                        var percentVal = percentComplete + '%';
                        bar.width(percentVal);
                        percent.html(percentVal);
                    }
                }, false);

                return xhr;
            },
            type : 'POST',
            url : '/api/post',
            data : postData,
            contentType: false,
            processData: false,
            beforeSend : function() {
                status.empty();
                var percentVal = '0%';
                bar.width(percentVal);
                percent.html(percentVal);
            },
            complete : function(xhr) {
                alert('성공');
            },
            error : function(error) {
                alert('실패');
            }
        }).done(function () {
            alert("업로드 성공");
            window.location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function() {

        $.ajax({
            type : 'DELETE',
            dataType : 'text',
            url : "/api/post/" + $('#postId').val(),
        }).done(function (result) {
            alert('게시물을 삭제 완료했습니다.');
            window.location.href="/postlist";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });


    },

    update : function() {

        var title = $('#title').val();
        var content = $('#content').val();
        var postId = $('#id').val();

        var postData = new FormData();
        postData.append('title', title);
        postData.append('content', content);
        postData.append('postId', postId);

        $.ajax({
            type : 'PUT',
            url : "/api/post/" + postId,
            contentType : false,
            processData : false,
            data : postData
        }).done(function(result) {
            alert("변경 성공");
            window.location.href="/post/" + postId;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    }
}

main.init();