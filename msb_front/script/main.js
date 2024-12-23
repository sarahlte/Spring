axios.get('http://localhost:7777/games')
  .then(function (response) {
    const game = document.querySelector("#game_id")
    // en cas de réussite de la requête
    response.data.forEach(data => {
        const option = `<option value="${data.id}">${data.title}</option>`;
        game.insertAdjacentHTML("beforeend", option);
    })
  })
  .catch(function (error) {
    // en cas d’échec de la requête
    console.log(error);
  })
axios.get('http://localhost:7777/players')
.then(function (response) {
// en cas de réussite de la requête
    const player = document.querySelector("#winner_id")
    response.data.forEach(data => {
        const option = `<option value="${data.id}">${data.nickname}</option>`;
        player.insertAdjacentHTML("beforeend", option); 
    })
})
.catch(function (error) {
// en cas d’échec de la requête
console.log(error);
})









