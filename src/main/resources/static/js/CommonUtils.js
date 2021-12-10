export function fetchAPI(url, method, body, redirect) {
    fetch(url, {
        method,
        body: JSON.stringify(body),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    })
        .then(resp => {
            resp.json()
            if (resp.status === 200) {
                window.location.href = redirect
            } else {
                alert('Fail Validate!')
            }
        })
        .catch(err => alert('Error!'))
}

export function handleCheckbox(checkboxElement, checkboxAll, reduceCheckboxChecked, checkboxChecked) {
    checkboxElement.addEventListener('change', (e) => {
        checkboxChecked(checkboxElement.checked)
        checkboxAll.forEach((element) => {
            element.checked = checkboxElement.checked
        })
    })

    var sumCheckedFirst = checkboxAll.length

    checkboxAll.forEach((element) => {
        element.addEventListener('change', (e) => {
            checkboxElement.checked = (sumCheckedFirst == reduceCheckboxChecked ? true : false)
            checkboxChecked(reduceCheckboxChecked)
        })
    })
}