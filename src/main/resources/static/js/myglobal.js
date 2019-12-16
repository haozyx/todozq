let jqAjax = function(param){
    return new Promise(function(resolve, reject){
        $.ajax({
            url: param.url,
            type: param.type,
            data: param.data || '',
            dataType: "json",
            success: function(data){
                resolve(data);
            },
            error: function(error){
                reject(error)
            }
        });
    });
};