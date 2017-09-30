$('document').ready(function(){
	/*
	Load friendList on start
	*/
	$.ajax({
            type: "GET",
            url: "http://127.0.0.1:8080/MVC-1/friends",
            success: function(result) {
				var friends = result.split(",");
				var friendList = $('#friend-list');
				friendList.html("");
				for (var i in friends) {
					friendList.append(addFriendHtml(friends[i]));
				}
			}
    });

	var addPanel = $('#add-friend-panel');
    // Socket
    var socket;
    // User id
    var cookieId = $('#userid').text();

    /*Support functions*/
	function addFriendHtml(username) {
		var html = "<div class='friend'><div class='row'><div class='col-lg-3'><img src='img11.jpg' class='img-rounded'></div><div class='col-lg-9'><p>" + username + "</p></div></div></div>";
		return html;
	}
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

	$('#open-add-panel').click(function(event) {
		event.preventDefault();
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

    $("#reset-friend-list").click(function(event){
        event.preventDefault();
        $.ajax({
            type: "GET",
            url: "http://127.0.0.1:8080/MVC-1/friends",
            success: function(result) {
                var friends = result.split(",");
                var friendList = $('#friend-list');
                friendList.html("");
                for (var i in friends) {
                    friendList.append(addFriendHtml(friends[i]));
                }
            }
        });
    });




})
