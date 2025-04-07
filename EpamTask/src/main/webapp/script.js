function filterProducts() {
    const selectedCategory = document.getElementById("category").value;
    const products = document.querySelectorAll(".product");

    products.forEach(product => {
        const productCategory = product.getAttribute("data-category");

        if (selectedCategory === "all" || selectedCategory === productCategory) {
            product.style.display = "block";
        } else {
            product.style.display = "none";
        }
    });
}
