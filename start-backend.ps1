$ErrorActionPreference = "Stop"
New-Item -ItemType Directory -Force -Path logs | Out-Null
$services = @(
  @{Name="registry"; Path="service-registry"},
  @{Name="product"; Path="product-service"},
  @{Name="user"; Path="user-service"},
  @{Name="notification"; Path="notification-service"},
  @{Name="order"; Path="order-service"},
  @{Name="gateway"; Path="api-gateway"}
)
foreach ($s in $services) {
  Write-Host "Starting $($s.Name)..."
  Start-Process powershell -ArgumentList "-NoExit","-Command","cd '$PWD\$($s.Path)'; mvn spring-boot:run *> '$PWD\logs\$($s.Name).log'" -WindowStyle Minimized
  Start-Sleep -Seconds 5
}
Write-Host "Backend started. Eureka: http://localhost:8761 | Gateway: http://localhost:8080"
