#!/usr/bin/env bash
for f in logs/*.pid; do [ -f "$f" ] && kill "$(cat "$f")" 2>/dev/null || true; done
echo "Backend stopped."
