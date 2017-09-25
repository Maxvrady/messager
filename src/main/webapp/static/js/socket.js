$('document').ready(function(){
	var addPanel = $('#add-friend-panel');
    // Socket
    var socket;
    // User id
    var cookieId = $('#userid').text();

    /*Support functions*/

    // Json convert function
    function convertObjectToJson(Obj) {
        var jsonString = JSON.stringify(Obj);
        console.log(jsonString);
        return jsonString;
    }
    // Json parse function
    function parseJSON(JSONString) {
        var jsonObj = JSON.parse(JSONString);
        return jsonObj;
    }

    /*End support function*/


    /*Socket function*/

    socket = new WebSocket("ws://localhost:8080/MVC-1/socket");
    socket.onopen = function() {
    //    socket.send("xyi");
    }

    socket.onmessage = function(event) {
        console.log(event.data);
        var jsonObj = parseJSON(event.data);
        console.log(jsonObj.id);
    }

    /*End socket function*/
    //$("button").click(function(){
        // Object to transfer json
        //var userObj = {
    	//id : cookieId,
    	//method : "registration",
        //data : $("#send-text").val()
    	//};

        //socket.send(convertObjectToJson(userObj));
    //});
	$('#open-add-panel').click(function() {
		addPanel.removeClass("hidden");
	});

	$('#add-friend-button').click(function() {
		var inputVal = $('#add-friend-input').val();
		$.ajax({
			type: "POST",
			url: "http://127.0.0.1:8080/MVC-1/friend-add?friend=" + inputVal,
			success: function(result) {console.log(result)}
		});

	});

    $("#send-button").click(function(){
        $.ajax({
            type: "GET",
            url: "http://127.0.0.1:8080/MVC-1/friends",
            success: function(result) { console.log(result) }
        });
    });




})