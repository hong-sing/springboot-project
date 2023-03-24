let index = {
    init : function () {
        let _this = this;
        $('#submitButton').on('click', function () {
            _this.search();
        });
    },
    search : function () {
        window.location.href = '/search';
    }
}

index.init();