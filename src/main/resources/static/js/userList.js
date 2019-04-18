function updateUsers(listOfUsers) {



     let userListRoot = document.getElementById('user-list');

      while(userListRoot.firstChild) {
             userListRoot.firstChild.remove();
      }

      for(let i = 0; i < listOfUsers.length; i++) {
           console.log("User[" + i + "]:" + listOfUsers[i].name);
           let li = document.createElement('li');
           li.innerHTML = listOfUsers[i].name;
           userListRoot.appendChild(li);
      }
}

updateUsers(model.users);
setInterval(function() {
  updateUsers(model.users);
}, 1000);

