    document.addEventListener("DOMContentLoaded", () => {
    const adListElement = document.getElementById("adList");
    const adList = JSON.parse(localStorage.getItem("adList")) || [];

    adList.forEach((ad) => {
        const item = document.createElement("div");
        item.className = "ad-item";
        item.innerHTML = `
        <strong>${ad.amount}원</strong> - ${ad.content}
        <br><small>${ad.createdAt}</small>
        `;

        item.onclick = () => {
        localStorage.setItem("selectedAd", JSON.stringify(ad));
        window.location.href = "advertisement_detail.html";
        };

    adListElement.prepend(item);
    });
    });