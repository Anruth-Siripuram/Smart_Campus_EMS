function updatePrice() {
    let tickets = parseInt(document.getElementById("tickets").value) || 0;
    let price = parseFloat(document.getElementById("price").innerText) || 0;

    let total = tickets * price;

    document.getElementById("total").innerText = total.toFixed(2);
}

function setTickets() {
    document.getElementById("ticketsInput").value =
        document.getElementById("tickets").value;
}

/* 🔥 IMPORTANT FIX */
window.onload = function () {
    updatePrice();
};