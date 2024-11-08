const laptopImage = document.getElementById('laptop');
const videoOverlay = document.getElementById('video-Overlay');

function resizeVideo(){
const laptopWidth = laptopImage.clientWidth;
const laptopHeight = laptopimage.clientHeight;

const laptopAspectRatio = laptopWidth/laptopHeight;
videoOverlay.width = laptopWidth;
vidioOverlay.height = laptopWidth/laptopAspectRatio;
}
