let index = {
    init : function () {
        let _this = this;
        $('#submitButton').on('click', function () {
            _this.search();
        });
    }
}

index.init();