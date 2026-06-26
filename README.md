# 🎨 Album Cover Blur

> Generate beautiful blurred UI backgrounds from album covers using **K-Means Color Clustering** and pure **Java AWT**.

<p align="center">

![Java](https://img.shields.io/badge/Java-17+-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![AWT](https://img.shields.io/badge/AWT-Graphics2D-orange?style=for-the-badge)
![Algorithm](https://img.shields.io/badge/K--Means-Clustering-blueviolet?style=for-the-badge)
![License](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

</p>

---

## ✨ Features

- 🎨 Automatic color palette extraction
- ⚡ Pure Java (no external libraries)
- 🧠 Custom K-Means implementation
- 🌈 Smooth glow backgrounds
- 📷 Works with any album cover
- 🚀 Fast enough for real-time applications

---

# 📸 Examples

## Bryson Tiller

<p align="center">
<img src="src/main/resources/bryson-tiller.png" width="240">
&nbsp;&nbsp;&nbsp;&nbsp;
➜
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="src/main/resources/bryson-tiller-background.png" width="430">
</p>

---

## Chris Brown

<p align="center">
<img src="src/main/resources/chris-brown.jpg" width="240">
&nbsp;&nbsp;&nbsp;&nbsp;
➜
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="src/main/resources/chris-brown.background.png" width="430">
</p>

---

## NBA YoungBoy

<p align="center">
<img src="src/main/resources/nba-youngboy.jpeg" width="240">
&nbsp;&nbsp;&nbsp;&nbsp;
➜
&nbsp;&nbsp;&nbsp;&nbsp;
<img src="src/main/resources/nba-youngboy.jpegbackground.png" width="430">
</p>

---

# 🏗 Pipeline

```text
Album Cover
      │
      ▼
┌────────────────────┐
│ ImageProcessor     │
│ Filter invalid RGB │
└────────────────────┘
      │
      ▼
┌────────────────────┐
│ K-Means Clustering │
│ Find dominant      │
│ colors             │
└────────────────────┘
      │
      ▼
┌────────────────────┐
│ Background         │
│ Generation         │
└────────────────────┘
      │
      ▼
Blurred UI Background
```

---

# ⚙️ How it works

## 1️⃣ Pixel Filtering

Before clustering, pixels are converted into the HSB color space.

Pixels are discarded when they are:

- too dark
- too bright
- nearly gray
- almost white

This prevents shadows and highlights from dominating the palette.

```
Saturation > 0.15
Brightness ∈ (0.15, 0.85)
```

---

## 2️⃣ K-Means Clustering

The filtered pixels are grouped into **4 dominant colors** using a custom implementation of the K-Means algorithm.

The resulting palette represents the overall mood of the album artwork.

---

## 3️⃣ Background Generation

Instead of applying an expensive blur filter, the four colors are placed into an **8×8 image**.

Java's bilinear interpolation scales it up to Full HD, automatically creating a smooth cloud-like gradient.

```
8 × 8
     ↓
1920 × 1080
```

---

# 🚀 Example

```java
ImageProcessor processor = new ImageProcessor();
List<Pixel> pixels = processor.extractPixels("./album_cover.jpg");

ColorClusteringService clustering = new ColorClusteringService();
ColorPalette palette = clustering.calculatePalette(pixels, 4);

BackgroundGenerationService background = new BackgroundGenerationService();

BufferedImage image =
        background.generateBackground(palette, 1920, 1080);

ImageIO.write(image, "png", new File("background.png"));
```

---

# 📚 Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Core language |
| Graphics2D | Rendering |
| BufferedImage | Image processing |
| HSB Color Model | Pixel filtering |
| K-Means | Color clustering |
| Bilinear Interpolation | Gradient generation |

