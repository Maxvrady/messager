function Dialog(username) {

    this.name = username;
    this.be_load = false;
    this.display = false;

    // Set friend html
    $('#friend-list').append("<a href='#'><div class='friend container-fluid'><div class='row'><div class='col-lg-3'><img src='img11.jpg' class='img-rounded'></div><div class='col-lg-9'><p>"+ username +"</p></div></div></div></a>")
    // Set dialog box
    $('#message-window #user-message').append("<div class='row hidden' id='" + this.name + "'></div>");
    this.dialogMessageContainer = $('#message-window #user-message').find('#' + this.name);

    this.addMessage = function(author, text) {
        this.dialogMessageContainer.append("<div class='message'><p>"+ author +"</p><p>"+ text +"</p></div>");
    }

    this.beLoad = function() {
        this.be_load = true;
    }

    this.isLoad = function() {
        if (this.be_load) {
            return true;
        }else{
            return false;
        }
    }

    this.displayOn = function() {
        if (this.display==false) {
            this.dialogMessageContainer.removeClass('hidden');
            this.display = true;
        }
    }

    this.hideDialog = function() {
        if (this.display==true) {
            this.dialogMessageContainer.addClass('hidden');
            this.display = false;
        }
    }

    this.getName = function() {
        return this.name;
    }
}

function AllController(){

    var self = this;

    this.activeDialog = null;
    this.userId = $('#session_id').text();
    this.addPanel = $('#add-friend-panel');
    this.friend_list = $('#friend-list');

    this.users = [];

    this.socket = new WebSocket("ws://127.0.0.1:8080/MVC-1/socket");
    this.socket.onopen = function() {
        var obj = { method : "registration", session_id : self.userId };
        self.socket.send(convertObjectToJson(obj));
    }
    this.socket.onmessage = function(event) {
        var json = parseJSON(event.data);
        for (i in this.users) {
            if (this.users[i]==json.to_send){
                this.users[i].addMessage(json.author, json.to_send, json.text);
                break;
            }
        }
    }
    // Json convert function
    function convertObjectToJson(Obj) {
        var jsonString = JSON.stringify(Obj);
        return jsonString;
    }
    // Json parse function
    function parseJSON(JSONString) {
        var jsonObj = JSON.parse(JSONString);
        return jsonObj;
    }

    function setFriendClick() {

        $('#friend-list .friend').click(function(event){
            var username = $(this).find('p').html();

            if (self.activeDialog!=null && self.activeDialog.getName()==username) {
                return;
            }

            if (self.activeDialog!=null) {
                self.activeDialog.hideDialog();
            }

            for (i in self.users) {

                if (self.users[i].getName()==username){
                    self.activeDialog = self.users[i];
                    self.activeDialog.displayOn();
                    break;
                }

            }

            console.log(self.activeDialog.getName());

            if (!self.activeDialog.isLoad()) {
                self.activeDialog.beLoad();
            }else{
                 return;
            }
            $.ajax({
                type:"GET",
                url : "http://127.0.0.1:8080/MVC-1/messages/" + username,

                success: function(result) {
                    console.log(result);
                    var jsonMessageList = result.split(", ");


                    for (i in jsonMessageList) {
                        var x = parseJSON(jsonMessageList[i]);
                        self.activeDialog.addMessage(x.author, x.text);
                    }
                    console.log(jsonMessageList);
                }

            });

        });
    }

    $('#send-button').click(function() {
        var inputVal = $('#send-input').val();
        var json = { method : "message", to : self.activeDialog.getName(), text : inputVal };
        self.activeDialog.addMessage("reboot", inputVal);
        console.log(convertObjectToJson(json));
        self.socket.send(convertObjectToJson(json));

    });

    $.ajax({
            type: "GET",
            url: "http://127.0.0.1:8080/MVC-1/friends",
            success: function(result) {
                var friends = result.split(",");
                var friendList = $('#friend-list');
                friendList.html("");
                for (var i in friends) {
                    var friend = new Dialog(friends[i]);
                    self.users.push(friend);
                }
                setFriendClick();
            }
    });






}

$(document).ready(function(){

    var controller = new AllController();

    // function setFriendEvent() {

    //     $('#friend-list .friend').click(function(event){
    //         var username = $(this).find('p').html();
    //         console.log(username);
    //         $.ajax({
    //             type:"GET",
    //             url : "http://127.0.0.1:8080/MVC-1/messages/" + username,
    //             success: function(result) {
    //                 console.log(result);
    //                 $('#messages').html("");
    //                 var jsonMessageList = result.split(", ");
    //                 for (i in jsonMessageList) {
    //                     var x = parseJSON(jsonMessageList[i]);
    //                     addMessage(x.author, x.text);
    //                 }
    //                 console.log(jsonMessageList);
    //             }
    //         });
    //     });

    // }

    // /*
    // Load friendList on start
    // */

    // var friendList = $('#friend-list');

    // var messages_panel = $('#messages');

    // var addPanel = $('#add-friend-panel');

    // var friends = $('#friend-list .friend');

    // console.log(friends);
    // // Socket
    // var socket;
    // // User id
    // var cookieId = $('#userid').text();

    // /*Support functions*/
    // function addMessage(author, text) {
    //     var html = "<div class='message'><p>"+ author +"</p><p>"+ text +"</p></div>";
    //     messages_panel.append(html);
    // }

    // function addFriendHtml(username) {
    //     var html = "<a href='#'><div class='friend container-fluid'><div class='row'><div class='col-lg-3'><img src='img11.jpg' class='img-rounded'></div><div class='col-lg-9'><p>"+ username +"</p></div></div></div></a>";
    //     // var html = "<a href='#''><div class='row friend'><div class='col-lg-3'><img src='img11.jpg' class='img-rounded'></div><div class='col-lg-9'><p>"+username+"</p></div></div></a>"
    //     friendList.append(html);
    // }
    // // Json convert function
    // function convertObjectToJson(Obj) {
    //     var jsonString = JSON.stringify(Obj);
    //     console.log(jsonString);
    //     return jsonString;
    // }
    // // Json parse function
    // function parseJSON(JSONString) {
    //     var jsonObj = JSON.parse(JSONString);
    //     return jsonObj;
    // }

    // /*End support function*/

    // $.ajax({
    //         type: "GET",
    //         url: "http://127.0.0.1:8080/MVC-1/friends",
    //         success: function(result) {
    //             var friends = result.split(",");
    //             var friendList = $('#friend-list');
    //             friendList.html("");
    //             for (var i in friends) {
    //                 friendList.append(addFriendHtml(friends[i]));
    //             }
    //             friendList = $('#friend-list');
    //             friends = $('#friend-list .friend');
    //             setFriendEvent();
    //      }
    // });

    // /*Socket function*/

    // socket = new WebSocket("ws://localhost:8080/MVC-1/socket");
    // socket.onopen = function() {
    // //    socket.send("xyi");
    // }

    // socket.onmessage = function(event) {
    //     console.log(event.data);
    //     var jsonObj = parseJSON(event.data);
    //     console.log(jsonObj.id);
    // }

    // /*End socket function*/

    // //$("button").click(function(){
    //     // Object to transfer json
    //     //var userObj = {
    //     //id : cookieId,
    //     //method : "registration",
    //     //data : $("#send-text").val()
    //     //};

    //     //socket.send(convertObjectToJson(userObj));
    // //});

    // $('#open-add-panel').click(function(event) {
    //     event.preventDefault();
    //     addPanel.removeClass("hidden");
    // });

    // $('#add-friend-button').click(function() {
    //     var inputVal = $('#add-friend-input').val();
    //     $.ajax({
    //         type: "POST",
    //         url: "http://127.0.0.1:8080/MVC-1/friend-add?friend=" + inputVal,
    //         success: function(result) {console.log(result)}
    //     });

    // });

    // $('#reset-friend-list').click(function(event){
    //     event.preventDefault();
    //     $.ajax({
    //         type: "GET",
    //         url: "http://127.0.0.1:8080/MVC-1/friends",
    //         success: function(result) {
    //             var friends = result.split(",");
    //             friendList.html("");
    //             for (var i in friends) {
    //                 addFriendHtml(friends[i]);
    //             }
    //             friendList = $('#friend-list');
    //             friends = $('#friend-list .friend');
    //             console.log(friends);
    //             setFriendEvent();
    //         }
    //     });

    // });



})
