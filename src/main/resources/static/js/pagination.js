//let currentPage = 0;
//let lastPage = false;
//
//function loadBlogs(page) {
//    if (page < 0 || lastPage) return;
//
//    fetch(`/user/home/blogs?page=` + page)
//        .then(response => response.text())
//        .then(html => {
//
//            // if no cards returned → stop
//            if (html.trim().length === 0) {
//                lastPage = true;
//                return;
//            }
//
//            document.getElementById("blogContainer").innerHTML = html;
//            currentPage = page;
//        })
//        .catch(err => console.error("Pagination error", err));
//}


function loadBlogs(page) {
    if (page < 0) return;

    fetch(`/user/home/blogs?page=` + page)
        .then(res => res.text())
        .then(html => {
            const container = document.getElementById("blogContainer");
            if (container) {
                container.innerHTML = html;
            }
        })
        .catch(err => console.error(err));
}

