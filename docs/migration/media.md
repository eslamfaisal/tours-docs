# Media Migration

## Image Processing

| Step | Action |
|------|--------|
| Download | Get original images |
| Optimize | Compress, resize |
| Upload | Push to CDN (S3/Cloudinary) |
| Link | Update content references |

## Video Hosting

Videos hosted on YouTube or Vimeo, embedded in vlogs.

## Naming Convention

```
trips/{trip-slug}/image-01.jpg
blog/{post-slug}/featured.jpg
destinations/{dest-slug}/hero.jpg
```
