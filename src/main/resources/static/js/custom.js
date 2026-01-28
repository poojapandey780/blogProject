// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();


// isotope js
$(window).on('load', function () {
    $('.filters_menu li').click(function () {
        $('.filters_menu li').removeClass('active');
        $(this).addClass('active');

        var data = $(this).attr('data-filter');
        $grid.isotope({
            filter: data
        })
    });

    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        percentPosition: false,
        masonry: {
            columnWidth: ".all"
        }
    })
});



//for penNmae check

    const penNameInput = document.getElementById("penName");
    const penNameMsg = document.getElementById("penNameMsg");

    function checkPenName() {
        const penName = penNameInput.value.trim();
        if (!penName) return;

        fetch(`/user/check-penname?penName=${penName}`)
            .then(res => res.json())
            .then(data => {
                if (data.available) {
                    penNameMsg.textContent = "Pen name is available ✔";
                    penNameMsg.className = "text-success";
                } else {
                    penNameMsg.textContent = "Pen name already taken ✖";
                    penNameMsg.className = "text-danger";
                }
            });
    }

    // When user presses Enter
    penNameInput.addEventListener("keydown", function (e) {
        if (e.key === "Enter") {
            e.preventDefault();
            checkPenName();
        }
    });

    // When user leaves the field
    penNameInput.addEventListener("blur", checkPenName);

