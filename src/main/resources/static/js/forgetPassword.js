document.addEventListener("DOMContentLoaded", () => {

    const emailForm = document.getElementById("forgotPassword");
    const otpForm = document.getElementById("otpForm");
    const resetForm = document.getElementById("resetPasswordForm");

    const errorMsg = document.getElementById("errorMessage");
    const successMsg = document.getElementById("successMessage");

    let messageTimer; // 🔥 important to avoid overlap

    function showMessage(type, msg) {
        // clear previous timer
        clearTimeout(messageTimer);

        errorMsg.style.display = "none";
        successMsg.style.display = "none";

        if (type === "success") {
            successMsg.textContent = msg;
            successMsg.style.display = "block";
        } else {
            errorMsg.textContent = msg;
            errorMsg.style.display = "block";
        }

        // ⏱ auto hide after 2 seconds
        messageTimer = setTimeout(() => {
            errorMsg.style.display = "none";
            successMsg.style.display = "none";
        }, 2000);
    }

    /* STEP 1: SEND OTP */
    emailForm.addEventListener("submit", e => {
        e.preventDefault();

        fetch(emailForm.action, {
            method: "POST",
            body: new FormData(emailForm),
            headers: { "X-Requested-With": "XMLHttpRequest" }
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                showMessage("success", data.message);
                emailForm.style.display = "none";
                otpForm.style.display = "block";
            } else {
                showMessage("error", data.message);
            }
        });
    });

    /* STEP 2: VERIFY OTP */
    otpForm.addEventListener("submit", e => {
        e.preventDefault();

        fetch(otpForm.action, {
            method: "POST",
            body: new FormData(otpForm),
            headers: { "X-Requested-With": "XMLHttpRequest" }
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                showMessage("success", data.message);
                otpForm.style.display = "none";
                resetForm.style.display = "block";
            } else {
                showMessage("error", data.message);
            }
        });
    });

    /* STEP 3: RESET PASSWORD */

    resetForm.addEventListener("submit", e => {
        e.preventDefault();

        const password = document.getElementById("password").value.trim();
        const confirmPassword = document.getElementById("confirmPassword").value.trim();

        //  client-side check
        if (password !== confirmPassword) {
            showMessage("error", "Password and Confirm Password must be same");
            return; //  stop here, do NOT send to controller
        }

        //  send only when passwords match
        fetch(resetForm.action, {
            method: "POST",
            body: new FormData(resetForm),
            headers: { "X-Requested-With": "XMLHttpRequest" }
        })
        .then(res => res.json())
        .then(data => {
            if (data.success) {
                showMessage("success", data.message);

                // ⏱ wait 2 sec then redirect
                setTimeout(() => {
                    window.location.href = "/user/login";
                }, 2000);

            } else {
                showMessage("error", data.message);
            }
        });
    });


});
