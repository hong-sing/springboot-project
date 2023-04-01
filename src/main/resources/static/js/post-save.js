let post_save = {
    init : function () {
        let _this = this;
        $('#write').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        let description = $('#summernote').summernote('code');
        let youtubeIframe = $(description).find('iframe[src*=youtube]');
        let reference = null;
        let login = $('#login').val();
        if (youtubeIframe.length > 0) {
            reference = youtubeIframe.attr('src').replace('//www.youtube.com/embed/', 'https://www.youtube.com/watch?v=');
            console.log(reference);
        }
        console.log(description);

        let data = {
            author: $('#author').val(),
            meme: $('#meme').val(),
            summary: $('#summary').val(),
            description: description,
            reference: reference,
            login: login
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('성공');
            window.location.href = '/api/v1/search/' + login;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

$(document).ready(function () {
    $('#summernote').summernote({
        // 에디터 높이
        height: 300,
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

post_save.init();

