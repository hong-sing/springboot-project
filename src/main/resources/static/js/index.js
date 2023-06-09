let index = {
    init : function () {
        // console.log("a");
        let _this = this;
        let streamer;
        $('#searchButton').on('click', function () {
            streamer = $('#streamer').val();
            if (streamer.length < 1) {
                alert('스트리머 ID를 입력해주세요');
            } else {
                _this.search();
            }
            // console.log("z");
        });
        $("#streamer").keydown(function(event) {
            streamer = $('#streamer').val();
            if (event.which === 13) {
                if (streamer.length < 1) {
                    alert('스트리머 ID를 입력해주세요');
                } else {
                    event.preventDefault(); // 기본 이벤트 방지
                    _this.search();
                }
            }
        });
    },
    search : function () {
        let streamer = $('#streamer').val();
        // window.location.href = '/search/' + streamer;

        $.ajax({
            type: 'GET',
            url: '/meme/post/' + streamer,
        }).done(function () {
            window.location.href = '/meme/post/' + streamer;
        }).fail(function (error) {
            alert('스트리머 ID를 다시 확인해주세요');
            // console.log(error);
        });
    }

}

index.init();