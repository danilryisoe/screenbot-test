#!/bin/sh
set -e

GRADLE_VERSION="8.9"
GRADLE_DIST_URL="https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip"
CACHE_DIR="${GRADLE_USER_HOME:-$HOME/.gradle}/wrapper/dists/gradle-${GRADLE_VERSION}-bin"
GRADLE_HOME_DIR="$CACHE_DIR/gradle-${GRADLE_VERSION}"

if [ ! -x "$GRADLE_HOME_DIR/bin/gradle" ]; then
  mkdir -p "$CACHE_DIR"
  ZIP_FILE="$CACHE_DIR/gradle.zip"
  curl -fsSL "$GRADLE_DIST_URL" -o "$ZIP_FILE"
  unzip -q -o "$ZIP_FILE" -d "$CACHE_DIR"
  rm -f "$ZIP_FILE"
  chmod +x "$GRADLE_HOME_DIR/bin/gradle"
fi

exec "$GRADLE_HOME_DIR/bin/gradle" "$@"
