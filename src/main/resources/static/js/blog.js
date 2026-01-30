//for penNmae check

    const blogTitleInput = document.getElementById("title");
    const blogTitleMsg = document.getElementById("titleNameMsg");

    if (blogTitleInput) {
//    1st check if title contain anything if no then do nothing
        function checkTitle() {
            const blogTitle = blogTitleInput.value.trim();
            if (!blogTitle) return;

//otherwise send to controller with get method
            fetch(`/user/blog/check-title?blogTitle=${blogTitle}`)
                .then(res => res.json())
                .then(data => {
                    if (data.available) {
                        blogTitleMsg.textContent = "Title is available ✔";
                        blogTitleMsg.className = "text-success";
                    } else {
                        blogTitleMsg.textContent = "Title already taken ✖";
                        blogTitleMsg.className = "text-danger";
                    }
                });
        }

// when the upper function run - when mouse enter
        blogTitleInput.addEventListener("keydown", e => {
            if (e.key === "Enter") {
                e.preventDefault();
                checkTitle();
            }
        });

// when
        blogTitleInput.addEventListener("blur", checkTitle);

        blogTitleInput.addEventListener("input", () => {
            blogTitleMsg.textContent = "";
            blogTitleMsg.className = "";
        });
    }



// for hisding message
document.addEventListener("DOMContentLoaded", () => {

    const successMsg = document.getElementById("successMsg");
    const errorMsg = document.getElementById("errorMsg");

    if (successMsg) {
        setTimeout(() => {
            successMsg.style.display = "none";
        }, 1000);
    }

    if (errorMsg) {
        setTimeout(() => {
            errorMsg.style.display = "none";
        }, 1000);
    }
});
