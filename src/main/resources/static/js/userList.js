function updateUsers(listOfUsers) {

     let userListRoot = document.getElementById('user-list');

      while(userListRoot.firstChild) {
             userListRoot.firstChild.remove();
      }

      for(let i = 0; i < listOfUsers.length; i++) {
           let li = document.createElement('li');
           li.innerHTML = listOfUsers[i].name;
           userListRoot.appendChild(li);
      }
}

updateUsers(model.users);
setInterval(function() {
  updateUsers(model.users);
}, 5000);

