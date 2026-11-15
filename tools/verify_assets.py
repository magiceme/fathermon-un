"""Fail if the supplied offline game or launcher icons were modified."""
from pathlib import Path
import hashlib
import json
import re

root = Path(__file__).resolve().parents[1]
manifest = json.loads((root / 'tools/original-assets.sha256.json').read_text())
for name, expected in manifest.items():
    actual = hashlib.sha256((root / name).read_bytes()).hexdigest()
    if actual != expected:
        raise SystemExit(f'Original asset changed: {name}')
html = (root / 'app/src/main/assets/index.html').read_text()
sprites = json.loads(re.search(r'const SPRITES\s*=\s*(\{.*?\});', html, re.S).group(1))
assert len(sprites) == 21, f'Expected 21 original sprites, found {len(sprites)}'
assert all(value.startswith('data:image/png;base64,') for value in sprites.values())
assert 'fathermon_un_journey_100_v1' in html
assert 'localStorage' in html
assert not re.search(r'https?://', html), 'Unexpected online asset dependency'
print('Verified: unchanged offline game, 21 embedded sprites, Journey save key, original icons.')
