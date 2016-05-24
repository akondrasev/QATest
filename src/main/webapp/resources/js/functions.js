function showErrorAndRedirect(response, $location) {
  showError(response);
  if ($location.path() != "/") {
    $location.path("/");
  }
}

function showError(response) {
  var $errorContainer = $("#error-msg-container");
  $errorContainer.find("#error-code").html(response.status);
  $errorContainer.find("#error-msg").html(response.statusText);
  $errorContainer.show();
}

function handleHttpCall($httpCall, $location) {
  return $httpCall.then(function (response) {
    return response;
  }, function (response) {
    showErrorAndRedirect(response, $location);
    return null;
  });
}
