let currentPage = 0;
let lastPage = false;

function loadBlogs(page) {
    if (page < 0 || lastPage) return;

    fetch(`/user/home/blogs?page=` + page)
        .then(response => response.text())
        .then(html => {

            // if no cards returned → stop
            if (html.trim().length === 0) {
                lastPage = true;
                return;
            }

            document.getElementById("blogContainer").innerHTML = html;
            currentPage = page;
        })
        .catch(err => console.error("Pagination error", err));
}
