let post = {
    init : function () {
        let _this = this;
        $('#write').on('click', function () {
            if (_this.isContent()) {
                _this.save();
            }
        });
        $('#update').on('click', function () {
            if (_this.isContent()) {
                _this.update();
            }
        });
        $('#delete').on('click', function () {
            if (confirm('정말 삭제하시겠습니까?')) {
                _this.delete();
            }
        });
        $('#updateForm').on('click', function () {
            _this.updateForm();
        });
        $('#saveMeme').on('click', function () {
            _this.goToMemeSavePage();
        })
    },
    goToMemeSavePage : function () {
        let isLogin = $('#isLogin').val();
        let login = $('#streamerLogin').val();
        if (isLogin != "" && isLogin != null) {
            window.location.href = '/meme/post-save/' + login;
        } else {
            alert('로그인 후 이용해주세요');
        }
    },
    updateForm : function () {
        let id = $('#id').val();
        window.location.href = '/meme/post-update/' + id;
    },
    save : function () {
        let description = $('#summernote').summernote('code');
        let youtubeIframe = $(description).find('iframe[src*=youtube]');
        let reference = null;
        let login = $('#login').val();
        if (youtubeIframe.length > 0) {
            reference = youtubeIframe.attr('src').replace('//www.youtube.com/embed/', 'https://www.youtube.com/watch?v=');
        }

        let data = {
            author: $('#author').val(),
            meme: $('#meme').val(),
            summary: $('#summary').val(),
            description: description,
            reference: reference,
            login: login,
            sub: $('#sub').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('밈이 등록되었습니다.');
            window.location.href = '/meme/post/' + login;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        let description = $('#summernote').summernote('code');
        let youtubeIframe = $(description).find('iframe[src*=youtube]');
        let reference = null;
        let login = $('#login').val();
        let id = $('#id').val();

        if (youtubeIframe.length > 0) {
            reference = youtubeIframe.attr('src').replace('//www.youtube.com/embed/', 'https://www.youtube.com/watch?v=');
        }

        let data = {
            author: $('#author').val(),
            meme: $('#meme').val(),
            summary: $('#summary').val(),
            description: description,
            reference: reference,
            login: login,
        };

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('밈이 수정되었습니다.');
            window.location.href = '/meme/post/' + login;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        let id = $('#id').val();
        let login = $('#login').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('밈이 삭제되었습니다.');
            window.location.href = '/meme/post/' + login;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    isContent : function () {
        let author = $('#author').val();
        let meme = $('#meme').val();
        let summary = $('#summary').val();

        if (author == null || author == '') {
            alert('작성자를 입력해주세요');
            return false;
        }
        if (meme == null || meme == '') {
            alert('밈을 입력해주세요');
            return false;
        }
        if (summary == null || summary == '') {
            alert('간단한 설명을 입력해주세요');
            return false;
        }
        return true;
    }
}

$(document).ready(function () {
    $('#summernote').summernote({
        // 에디터 높이
        height: 500,
        // 에디터 한글 설정
        lang: "ko-KR",
        // 에디터에 커서 이동 (input창의 autofocus라고 생각)
        focus : true,
        toolbar: [
            // 글꼴 설정
            ['fontname', ['fontname']],
            // 글자 크기 설정
            ['fontsize', ['fontsize']],
            // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
            ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
            // 글자색
            ['color', ['forecolor','color']],
            // 표만들기
            ['table', ['table']],
            // 글머리 기호, 번호매기기, 문단정렬
            ['para', ['ul', 'ol', 'paragraph']],
            // 줄간격
            ['height', ['height']],
            // 첨부
            ['insert', ['link', 'picture', 'video']],
            // 코드보기, 확대해서보기, 도움말
            ['view', ['codeview','fullscreen', 'help']]
        ],
        // 추가한 글꼴
        fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
        // 추가한 폰트사이즈
        fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    });
});

post.init();

